package com.zvarych.studlib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.zvarych.studlib.databinding.ActivitySignUpBinding
import com.zvarych.studlib.classes.User

class SignUp : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val utype = resources.getStringArray(R.array.users_type)
        val spinner_user = binding.usersSpinner
        var user: String = utype[0]
        spinner_user.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, utype)
        spinner_user.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                user = utype[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }
        auth = FirebaseAuth.getInstance()
        val department_type = resources.getStringArray(R.array.departments)
        var department = department_type[0]
        val spinner_department = binding.departmentSpinner
        spinner_department.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, department_type)
        spinner_department.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                department = department_type[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        val name = binding.name
        val email = binding.email
        val pass = binding.password


        binding.btnSignUn.setOnClickListener {
            if (name.text.isEmpty()) {
                name.setError("Name is empty!")
            }
            if (email.text.isEmpty()) {
                email.setError("Email is empty")
            }
            if (pass.text.length < 8) {
                pass.setError("Password is short")
            }
            if (name.text.isNotEmpty() && email.text.isNotEmpty() && pass.text.length > 7) {
                signup(
                    name.text.toString(),
                    email.text.toString(),
                    pass.text.toString(),
                    user,
                    department
                )
            }
        }

        binding.guestLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            finish()
            startActivity(intent)
        }

    }

    private fun signup(
        name: String,
        email: String,
        password: String,
        user: String,
        department: String
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                addtoDataBase(name, email, user, department, auth.currentUser?.uid!!)
                val intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            } else {
                Toast.makeText(this, "Smth go wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addtoDataBase(
        name: String,
        email: String,
        user: String,
        department: String,
        uid: String
    ) {
        database = FirebaseDatabase.getInstance().getReference()

        database.child("user").child(uid)
            .setValue(User(name, email, user, department, uid, false))
    }
}