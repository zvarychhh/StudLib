package com.zvarych.studlib

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.zvarych.studlib.databinding.ActivityAddBookBinding
import com.google.firebase.database.DatabaseReference
import com.zvarych.studlib.classes.Book
import java.util.UUID


class AddBookActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityAddBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = binding.tittle
        val author = binding.author
        val date = binding.date
        val count = binding.count

        binding.btnSaveBook.setOnClickListener {
            if (title.text.isEmpty()) {
                title.setError("Is Empty!")
            }
            if (author.text.isEmpty()) {
                author.setError("Is Empty!")
            }
            if (date.text.isEmpty()) {
                date.setError("Is Empty!")
            }
            if (count.text.isEmpty()) {
                count.setError("Is Empty!")
            }

            if (title.text.isNotEmpty() && author.text.isNotEmpty() && date.text.isNotEmpty() && count.text.isNotEmpty()) {
                addtoDataBase(
                    title.text.toString(),
                    author.text.toString(),
                    date.text.toString(),
                    UUID.randomUUID().toString(),
                    count.text.toString().toInt()
                )
            }
        }
    }

    private fun addtoDataBase(
        title: String, author: String, date: String, uid: String, count: Int
    ) {
        database = FirebaseDatabase.getInstance().getReference()
        database.child("book").child(uid).setValue(Book(title, author, date, uid, count))
    }
}