package com.example.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    lateinit var btnLoginTwo:Button
    lateinit var tilFirstName:TextInputLayout
    lateinit var tilLastName:TextInputLayout
    lateinit var btnSignUp:Button
    lateinit var tilPasswordTwo:TextInputLayout
    lateinit var tilEmailTwo:TextInputLayout
    lateinit var tilConfirm:TextInputLayout
    lateinit var etFirstName:EditText
    lateinit var etLastName:EditText
    lateinit var etConfirm:EditText
    lateinit var etPasswordTwo:EditText
    lateinit var etEmailTwo:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btnLoginTwo=findViewById(R.id.btnLoginTwo)
        tilFirstName=findViewById(R.id.tilFirstName)
        tilLastName=findViewById(R.id.tilLastName)
        btnSignUp=findViewById(R.id.btnSignUp)
        tilConfirm=findViewById(R.id.tilConfirm)
        tilEmailTwo=findViewById(R.id.tilEmailTwo)
        tilPasswordTwo=findViewById(R.id.tilPasswordTwo)
        etConfirm=findViewById(R.id.etConfirm)
        etFirstName=findViewById(R.id.etFirstname)
        etLastName=findViewById(R.id.etLastName)
        etEmailTwo=findViewById(R.id.etEmailTwo)
        etPasswordTwo=findViewById(R.id.etPasswordTwo)
        btnSignUp.setOnClickListener { validateSignup() }
        btnLoginTwo.setOnClickListener {
            val intent= Intent(this,loginActivity::class.java)
            startActivity(intent)

        }

    }
    fun validateSignup(){
        var error=false
        tilFirstName.error=null
        tilLastName.error=null
        tilEmailTwo.error=null
        tilPasswordTwo.error=null
        tilConfirm.error=null
        var email=etEmailTwo.text.toString()
        if (email.isBlank()){
            tilEmailTwo.error="Email is required"
            error=true
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tilEmailTwo.error="Not a valid email address"
            error=true
        }
        var password=etPasswordTwo.text.toString()
        if (password.isBlank()){
            tilPasswordTwo.error="Password is required"
            error=true
        }
        var firstName=etFirstName.text.toString()
        if (firstName.isBlank()) {
            tilFirstName.error = "FirstName is required"
            error=true
        }
        var lastName=etLastName.text.toString()
        if (lastName.isBlank()) {
            tilLastName.error = "lastName is required"
            error=true
        }
        var Confirm=etConfirm.text.toString()
        if (Confirm.isBlank()) {
            tilConfirm.error = "Confirmation is required"
        }
        if (!error){

        }
        if (password!=Confirm){
            tilConfirm.error="Invalid confirmation"
        }

    }

}