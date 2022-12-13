package com.zvarych.studlib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.zvarych.studlib.classes.Book

class LibraryBookAdapter(var booklist: ArrayList<Book>) :
    RecyclerView.Adapter<LibraryBookAdapter.MyViewHolder>() {
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.book_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curentitem = booklist[position]

        holder.title.text = curentitem.title
        holder.author.text = curentitem.author
        holder.date.text = curentitem.date
        holder.btn.setOnClickListener {
            database = FirebaseDatabase.getInstance().getReference()
            auth = Firebase.auth
            database.child("user").child(auth.currentUser!!.uid).child("book")
                .child(curentitem.uid.toString())
                .setValue(curentitem)

        }
    }

    override fun getItemCount(): Int {
        return booklist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.book_card_title)
        val author: TextView = itemView.findViewById(R.id.book_card_author)
        val date: TextView = itemView.findViewById(R.id.book_card_date)
        val btn: Button = itemView.findViewById(R.id.btn_your_book)
    }
}