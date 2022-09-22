package com.example.splashscreen.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.splashscreen.R
import com.example.splashscreen.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
   lateinit var binding:ActivityHomeBinding
   lateinit var sharePrefs:SharedPreferences
   lateinit var tvLogout:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tvLogout=findViewById(R.id.tvLogout)
        tvLogout.setOnClickListener {
            val editor=sharePrefs.edit()
            editor.putString("ACCESS_TOKEN","")
            editor.putString("USER_ID","")
            editor.putString("PROFILE_ID","")
            editor.apply()
            startActivity(Intent(this,loginActivity::class.java))
            logOutRequest()
        }
        castViews()
        setUpBottomNav()
    }
    fun castViews(){
        binding.fcvHome
        binding.bottomNavigation

    }
    fun setUpBottomNav(){
        binding.bottomNavigation.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.plan ->{
               var transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome, PlanFragment())
                    transaction.commit()
                    true
                }
                R.id.track ->{
                    var transaction=supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        TrackFragment()
                    ).commit()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        profileFragment()
                    ).commit()
                    true
                }
                else->false
            }
        }
    }
    fun logOutRequest(){
        sharePrefs.edit().clear().commit()
    }
}