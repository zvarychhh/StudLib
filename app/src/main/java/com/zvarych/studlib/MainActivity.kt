package com.zvarych.studlib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth


import com.zvarych.studlib.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLibBook.setOnClickListener {
            val intent = Intent(this, LibraryBookActivity::class.java)
            startActivity(intent)
        }

        binding.btnYourBook.setOnClickListener {
            val intent = Intent(this, YourBookActivity::class.java)
            startActivity(intent)
        }
    }
}