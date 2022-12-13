package com.zvarych.studlib

import android.util.Log
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

class YourbookAdapter(private val bookList: ArrayList<Book>) :
    RecyclerView.Adapter<YourbookAdapter.YourViewHolder>() {
    private lateinit var database: DatabaseReference
    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.ybook_card, parent, false
            )
        return YourViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: YourViewHolder, position: Int) {
        val curentitem = bookList[position]

        holder.title.text = curentitem.title
        holder.author.text = curentitem.author
        holder.date.text = curentitem.date
        database = FirebaseDatabase.getInstance().getReference("user")
        holder.btn.setOnClickListener {

            database.child(auth.currentUser!!.uid).child("book").child(curentitem.uid.toString())
                .removeValue()
            bookList.clear()
            notifyDataSetChanged()



            Log.e("data", curentitem.toString())



        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class YourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.ybook_card_title)
        val author: TextView = itemView.findViewById(R.id.ybook_card_author)
        val date: TextView = itemView.findViewById(R.id.ybook_card_date)
        val btn: Button = itemView.findViewById(R.id.ybtn_your_book)
    }
}