package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.anyjob.anyjob.R

class HomeActivity : AppCompatActivity() {

    //declare eliments
    private lateinit var btnNewJob: Button
    private lateinit var btnMyJob: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //initialize button
        btnNewJob = findViewById(R.id.btnNewJob)
        btnMyJob = findViewById(R.id.btnMyJobs)

        //set click
        btnNewJob.setOnClickListener{
            val intent = Intent(this, JobInsertActivity::class.java)
            startActivity(intent)
        }
        btnMyJob.setOnClickListener{
            val intent = Intent(this, MyJobsActivity::class.java)
            startActivity(intent)
        }

    }


}