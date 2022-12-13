package com.zvarych.studlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.zvarych.studlib.classes.Book

class LibraryBookActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var bookRecyclerView: RecyclerView
    private lateinit var bookArrayList: ArrayList<Book>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_book)

        bookRecyclerView = findViewById(R.id.booklist)
        bookRecyclerView.layoutManager = LinearLayoutManager(this)
        bookRecyclerView.setHasFixedSize(true)

        bookArrayList = arrayListOf<Book>()
        getBookData()
    }

    private fun getBookData() {
        database = FirebaseDatabase.getInstance().getReference("book")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (bookSnapshot in snapshot.children) {
                        val book = bookSnapshot.getValue(Book::class.java)
                        bookArrayList.add(book!!)
                    }
                    bookRecyclerView.adapter = LibraryBookAdapter(bookArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}