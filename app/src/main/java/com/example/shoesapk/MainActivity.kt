package com.example.shoesapk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.MenuItem
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var Bottomnav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // val buy_fragment = buyer
        Bottomnav = findViewById(R.id.bottom_menu)
        // set a listener to it
        Bottomnav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.seller_menu -> {
                var seller_intent = Intent(this, uploadform::class.java)
                startActivity(seller_intent)
            }
            R.id.buyer_menu -> {
                var buyer_intent = Intent(this, buyer::class.java)
                startActivity(buyer_intent)
            }
            R.id.browse_menu -> {
                var browser_intent = Intent(this, browser::class.java)
                startActivity(browser_intent)
            }
        }
       return true
    }
}




