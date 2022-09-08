package com.example.splashscreen.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.splashscreen.R
import com.example.splashscreen.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
   lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
        setUpBottomNav()
    }
    fun castViews(){

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
}