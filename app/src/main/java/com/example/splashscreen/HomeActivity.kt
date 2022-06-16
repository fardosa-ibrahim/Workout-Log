package com.example.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var bottom_navigation:BottomNavigationView
    lateinit var fcvHome:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        castViews()
        setUpBottomNav()
    }
    fun castViews(){
        bottom_navigation=findViewById(R.id.bottom_navigation)
        fcvHome=findViewById(R.id.fcvHome)
    }
    fun setUpBottomNav(){
        bottom_navigation.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.plan->{
               var transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome,PlanFragment())
                    transaction.commit()
                    true
                }
                R.id.track->{
                    var transaction=supportFragmentManager.beginTransaction().replace(R.id.fcvHome,TrackFragment()).commit()
                    true
                }
                R.id.profile->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome,profileFragment()).commit()
                    true
                }
                else->false
            }
        }
    }
}