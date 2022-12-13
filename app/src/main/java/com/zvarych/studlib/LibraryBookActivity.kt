package com.zvarych.studlib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.zvarych.studlib.classes.Book

class LibraryBookActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var bookRecyclerView: RecyclerView
    private lateinit var bookArrayList: ArrayList<Book>
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_book)

        bookRecyclerView = findViewById(R.id.booklist)
        bookRecyclerView.layoutManager = LinearLayoutManager(this)
        bookRecyclerView.setHasFixedSize(true)
        auth = Firebase.auth
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.return_back) {
            finish()
            return true
        }
        if (item.itemId == R.id.log_out) {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}