package com.zvarych.studlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.zvarych.studlib.classes.Book
import com.zvarych.studlib.databinding.ActivityYourBookBinding

class YourBookActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var bookRecyclerView: RecyclerView
    private lateinit var bookArrayList: ArrayList<Book>
    private lateinit var binding: ActivityYourBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        bookRecyclerView = binding.ybooklist
        bookRecyclerView.layoutManager = LinearLayoutManager(this)
        bookRecyclerView.setHasFixedSize(true)

        bookArrayList = arrayListOf<Book>()
        getbookdata()

    }

    private fun getbookdata() {
        database = FirebaseDatabase.getInstance().getReference("user").child(auth.currentUser!!.uid)
            .child("book")

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (bookSnapshot in snapshot.children) {
                        val book = bookSnapshot.getValue(Book::class.java)
                        bookArrayList.add(book!!)
                    }
                bookRecyclerView.adapter = YourbookAdapter(bookArrayList)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}