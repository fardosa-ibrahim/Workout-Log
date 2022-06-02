package com.example.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.ActionMode
import androidx.appcompat.app.AppCompatActivity

class splash_screen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this,loginActivity::class.java))

    }
}