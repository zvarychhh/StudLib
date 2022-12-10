package com.zvarych.studlib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.zvarych.studlib.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {



    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val utype = resources.getStringArray(R.array.users_type)
        val spinner_user = binding.usersSpinner
        var user: String = utype[0]
        spinner_user.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, utype)
        spinner_user.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                user = utype[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }
        val department_type = resources.getStringArray(R.array.departments)
        var department = department_type[0]
        val spinner_department = binding.departmentSpinner
        spinner_department.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, department_type)
        spinner_department.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                department = department_type[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        val name = binding.name.text
        val email = binding.email.text
        val pass = binding.password.text


        binding.btnSignUn.setOnClickListener {
            Toast.makeText(this, "$name $email $pass $user $department", Toast.LENGTH_SHORT).show()
        }

        binding.guestLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            finish()
            startActivity(intent)
            // NEW
        }





    }

    private fun signup(name: String) {

    }
}