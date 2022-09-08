package com.example.splashscreen.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.splashscreen.api.ApiInterface
import com.example.splashscreen.api.apiClient
import com.example.splashscreen.databinding.ActivitySignUpBinding
import com.example.splashscreen.models.RegisterRequest
import com.example.splashscreen.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            val intent= Intent(this, loginActivity::class.java)
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
        binding.tilPhone.error=null
        val email=binding.etEmailTwo.text.toString()
        if (email.isBlank()){
            binding.tilEmailTwo.error="Email is required"
            error=true
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmailTwo.error="Not a valid email address"
            error=true
        }
        val password=binding.etPasswordTwo.text.toString()
        if (password.isBlank()){
            binding.tilPasswordTwo.error="Password is required"
            error=true
        }
        val firstName=binding.etFirstname.text.toString()
        if (firstName.isBlank()) {
            binding.tilFirstName.error = "FirstName is required"
            error=true
        }
        val lastName=binding.etLastName.text.toString()
        if (lastName.isBlank()) {
            binding.tilLastName.error = "lastName is required"
            error=true
        }
        val Confirm=binding.etConfirm.text.toString()
        if (Confirm.isBlank()) {
            binding.tilConfirm.error = "number is required"
        }
        if (!error){

        }
        val phoneNumber=binding.etPhone.text.toString()
        if (phoneNumber.isBlank()) {
            binding.tilPhone.error = "Confirmation is required"
        }
        if (!error){

        }
        if (password!=Confirm){
            binding.tilConfirm.error="Invalid confirmation"
        }

        if(!error){
            val registerRequest=RegisterRequest(firstName,lastName,phoneNumber,email,password)
            makeRegisterRequest(registerRequest)
            startActivity(Intent(this,loginActivity::class.java))
        }
    }
    fun makeRegisterRequest(registerRequest: RegisterRequest){
        val apiClient= apiClient.buildApiClient(ApiInterface::class.java)
        val request=apiClient.registerUser(registerRequest)

        request.enqueue(object :Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                 if (response.isSuccessful){
                  Toast.makeText(baseContext,response.body()?.message,Toast.LENGTH_LONG).show()
                 }
                else{
                    val error=response.errorBody()?.string()
                     Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                 }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
               Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

        })

    }

}