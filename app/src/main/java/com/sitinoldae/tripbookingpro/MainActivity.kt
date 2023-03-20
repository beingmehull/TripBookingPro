package com.sitinoldae.tripbookingpro

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button

private lateinit var travelPackagesBtn:Button;
private lateinit var romanticTravelPackagesBtn:Button;
private lateinit var addTripBtn:Button;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hiding status bar for fullscreen
        hideStatusBar()
        setContentView(R.layout.activity_main)
        travelPackagesBtn=findViewById(R.id.travel_packages_btn);
        romanticTravelPackagesBtn=findViewById(R.id.romantic_travel_packages_btn);
        addTripBtn=findViewById(R.id.add_trip_btn);

        travelPackagesBtn.setOnClickListener {
            val intent = Intent(this, TravelPackages::class.java)
            startActivity(intent)
        }
        romanticTravelPackagesBtn.setOnClickListener {
            val intent = Intent(this, RomanticTravelPackages::class.java)
            startActivity(intent) }

        addTripBtn.setOnClickListener {
            val intent = Intent(this, AddTrips::class.java)
            startActivity(intent)
        }
    }
    private fun hideStatusBar() {
       //on create
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}