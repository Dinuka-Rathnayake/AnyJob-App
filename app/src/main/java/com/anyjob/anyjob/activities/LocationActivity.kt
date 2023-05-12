package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.anyjob.anyjob.R

class LocationActivity : AppCompatActivity() {

    //declare
    private lateinit var jobLocation : EditText
    private lateinit var btnLocationNext : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        //initialize
        jobLocation = findViewById(R.id.etJobLocation)
        btnLocationNext = findViewById(R.id.btnLocationNext1_d)

        //next btn function
        btnLocationNext.setOnClickListener{
            val Locationintent = Intent(this, JobInsertActivity::class.java)
//            Log.d("myTag", jobLocation.text.toString())
            Locationintent.putExtra("location", jobLocation.text.toString())
            startActivity(Locationintent)
        }

    }
}