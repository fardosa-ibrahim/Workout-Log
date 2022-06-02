package com.example.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class loginActivity : AppCompatActivity() {
    lateinit var btnLogin:Button
    lateinit var tilEmail:TextInputLayout
    lateinit var etEmail:TextInputEditText
    lateinit var tilPassword:TextInputLayout
    lateinit var etPassword:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        castViews()
    }
    fun castViews(){
        btnLogin=findViewById(R.id.btnLogin)
        tilEmail=findViewById(R.id.tilEmail)
        etEmail=findViewById(R.id.etEmail)
        tilPassword=findViewById(R.id.tilPassword)
        etPassword=findViewById(R.id.etPassword)
        btnLogin.setOnClickListener{validateLogin()}
    }
    fun validateLogin(){
        var error=false
        tilPassword.error=null
        tilEmail.error=null
        var email=etEmail.text.toString()
        if (email.isBlank()){
            tilEmail.error="Email is required"
            error=true
        }
        var password=etPassword.text.toString()
        if (password.isBlank()){
            tilPassword.error="Password is required"
        }

        }

    }
