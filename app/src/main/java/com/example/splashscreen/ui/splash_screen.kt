package com.example.splashscreen.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class splash_screen: AppCompatActivity() {
lateinit var sharedPrefs:SharedPreferences
lateinit var logout:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        logout=getSharedPreferences("LOGOUT", MODE_PRIVATE)


        val accessToken=sharedPrefs.getString("ACCESS_TOKEN","")
        val deleteToken=sharedPrefs.getString("EMAIL","")

        if(accessToken!!.isBlank()){
            startActivity(Intent(this, loginActivity::class.java))
        }
        else{
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}