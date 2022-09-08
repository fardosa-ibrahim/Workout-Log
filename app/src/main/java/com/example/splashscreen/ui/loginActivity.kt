package com.example.splashscreen.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.splashscreen.databinding.ActivityLoginBinding

class loginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
    }


    fun castView() {
        binding.btnLogin.setOnClickListener {
            validateLogin()
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }

    fun validateLogin() {
        var error = false
        binding.tilPasswordOne.error=null
        binding.tilEmailOne.error=null

        var email = binding.etEmail.text.toString()
        if (email.isBlank()) {
            binding.tilEmailOne.error = "Email is required"
            error = true
        }
        var password = binding.etPasswordOne.text.toString()
        if (password.isBlank()) {
            binding.tilPasswordOne.error = "Password is required"
            error = true
        }
        if (!error) {

        }
    }
}




