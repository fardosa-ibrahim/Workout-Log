package com.example.splashscreen

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import com.example.splashscreen.databinding.ActivitySignUpBinding
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
  lateinit var binding:ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
    }
    fun castView(){
        binding.btnSignUp.setOnClickListener { validateSignup() }
        binding.btnLoginTwo.setOnClickListener {
            val intent= Intent(this,loginActivity::class.java)
            startActivity(intent)
        }
    }
    fun validateSignup(){
        var error=false
        binding.tilFirstName.error=null
        binding.tilLastName.error=null
        binding.tilEmailTwo.error=null
        binding.tilPasswordTwo.error=null
        binding.tilConfirm.error=null
        var email=binding.etEmailTwo.text.toString()
        if (email.isBlank()){
            binding.tilEmailTwo.error="Email is required"
            error=true
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmailTwo.error="Not a valid email address"
            error=true
        }
        var password=binding.etPasswordTwo.text.toString()
        if (password.isBlank()){
            binding.tilPasswordTwo.error="Password is required"
            error=true
        }
        var firstName=binding.etFirstname.text.toString()
        if (firstName.isBlank()) {
            binding.tilFirstName.error = "FirstName is required"
            error=true
        }
        var lastName=binding.etLastName.text.toString()
        if (lastName.isBlank()) {
            binding.tilLastName.error = "lastName is required"
            error=true
        }
        var Confirm=binding.etConfirm.text.toString()
        if (Confirm.isBlank()) {
            binding.tilConfirm.error = "Confirmation is required"
        }
        if (!error){

        }
        if (password!=Confirm){
            binding.tilConfirm.error="Invalid confirmation"
        }

    }

}