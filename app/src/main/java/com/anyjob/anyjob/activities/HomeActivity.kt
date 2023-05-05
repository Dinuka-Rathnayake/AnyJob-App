package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.anyjob.anyjob.R

class HomeActivity : AppCompatActivity() {

    //declare eliments
    private lateinit var btnNewJob: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //initialize button
        btnNewJob = findViewById(R.id.btnNewJob)

        //set click
        btnNewJob.setOnClickListener{
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
    }


}