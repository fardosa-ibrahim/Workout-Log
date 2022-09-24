package com.example.splashscreen.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.splashscreen.ViewModel.UserViewModel
import com.example.splashscreen.api.ApiInterface
import com.example.splashscreen.api.ApiClient
import com.example.splashscreen.databinding.ActivityLoginBinding
import com.example.splashscreen.models.RegisterRequest
import com.example.splashscreen.models.loginRequest
import com.example.splashscreen.models.loginResponce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharePrefs: SharedPreferences
    val userViewModel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
        sharePrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

    }

    override fun onResume(){
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponce->
            Toast.makeText(baseContext, loginResponce?.message,Toast.LENGTH_LONG).show()
            saveLoginDetains(loginResponce!!)
            startActivity(Intent(baseContext, HomeActivity::class.java))
        })
        userViewModel.errorLiveData.observe(this, Observer { errorMessage->
            Toast.makeText(baseContext, errorMessage, Toast.LENGTH_LONG).show()
        })
    }

    fun castView() {
        binding.btnLogin.setOnClickListener {
            validateLogin()
//            startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    fun validateLogin() {
        var error = false
        binding.tilPasswordOne.error = null
        binding.tilEmailOne.error = null

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
            val loginRequest = loginRequest(email, password)
            binding.pbLogin.visibility = View.VISIBLE
            userViewModel.loginUser(loginRequest)
        }
    }

    fun saveLoginDetains(loginResponce: loginResponce) {
        val editor = sharePrefs.edit()
        editor.putString("ACCESS_TOKEN", loginResponce.accessToken)
        editor.putString("USER_ID", loginResponce.userId)
        editor.putString("PROFILE_ID", loginResponce.profileId)
        editor.apply()

    }
    }










