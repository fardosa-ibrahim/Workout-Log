package com.example.splashscreen.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.splashscreen.api.ApiInterface
import com.example.splashscreen.api.apiClient
import com.example.splashscreen.databinding.ActivityLoginBinding
import com.example.splashscreen.models.loginRequest
import com.example.splashscreen.models.loginResponce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharePrefs: SharedPreferences
    lateinit var logout: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
        sharePrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        logout = getSharedPreferences("LOGOUT", MODE_PRIVATE)

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
            makeLoginRequest(loginRequest)
        }
    }

    fun makeLoginRequest(loginRequest: loginRequest) {
        val apiClient = apiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.loginUser(loginRequest)

        request.enqueue(object : Callback<loginResponce> {
            override fun onResponse(call: Call<loginResponce>, response: Response<loginResponce>) {
                binding.pbLogin.visibility = View.GONE
                if (response.isSuccessful) {
                    val loginResponce = response.body()
                    Toast.makeText(baseContext, loginResponce?.message, Toast.LENGTH_LONG).show()
                    saveLoginDetains(loginResponce!!)
                    startActivity(Intent(baseContext, HomeActivity::class.java))
                } else {
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                    logout(loginRequest!!)
                }
            }

            override fun onFailure(call: Call<loginResponce>, t: Throwable) {
                binding.pbLogin.visibility = View.GONE
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })


    }

    fun saveLoginDetains(loginResponce: loginResponce) {
        val editor = sharePrefs.edit()
        editor.putString("ACCESS_TOKEN", loginResponce.accessToken)
        editor.putString("USER_ID", loginResponce.userId)
        editor.putString("PROFILE_ID", loginResponce.profileId)
        editor.apply()

    }

    fun logout(loginRequest: loginRequest) {
        val editor = logout.edit()
        editor.putString("EMAIL", loginRequest.email)
        editor.putString("PASSWORD", loginRequest.password)
        editor.apply()
    }
}







