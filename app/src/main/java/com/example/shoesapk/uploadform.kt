package com.example.shoesapk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_uploadform.*

lateinit var brandSpinner:Spinner
lateinit var sellerBottom:BottomNavigationItemView
class uploadform : AppCompatActivity(),AdapterView.OnItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploadform)

        brandSpinner = findViewById(R.id.brandspinner)
        ArrayAdapter.createFromResource(this, R.array.shoe_brands, android.R.layout.simple_spinner_item).also { adapter ->
            //specify the list of choices to show hwen the spinner appears.
             adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

                 // apply the adapter to the spinner
            brandSpinner.adapter = adapter

        }


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //retrieving the selected item
        brandSpinner.onItemSelectedListener=this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "select brand to sell", Toast.LENGTH_LONG).show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.seller_stats -> {
                val sellerStats = Intent(this, sellerstats::class.java)
                startActivity(sellerStats)
            }
            R.id.seller_profile ->{
                val sellerProfile = Intent(this, sellerprofile::class.java)
                startActivity(sellerProfile)
            }


        }
        return true
    }

    fun SubmitDetails(view: View){
        // capture user details
        val userid = userid_edit.text.toString()
        val brand = brandspinner.selectedItem.toString()
        val price = price_edit.text.toString()
        val contact = phone_edit.text.toString()
        val names =name_edit.text.toString()

        // create an instance of our shoe database
         val databasehelper = shoesDatabase(this)

        // validation
        if (price.trim()!="" && contact.trim()!="" && names!="" &&brand!=" "){
            val status = databasehelper.addseller(sqliteModel(Integer.valueOf(price),brand,names, Integer.valueOf(contact),Integer.valueOf(price)))
            if (status>-1){
                Toast.makeText(this,"record saved", Toast.LENGTH_LONG).show()

                price_edit.text?.clear()
                phone_edit.text?.clear()
                name_edit.text?.clear()
               // brandspinner.selectedItem.clear
            }
            else{
                Toast.makeText(this,"try again! something went wrong", Toast.LENGTH_LONG).show()
            }

        }
        else{
            Toast.makeText(this,"fields cannot be Empty", Toast.LENGTH_LONG).show()
        }


    }


}