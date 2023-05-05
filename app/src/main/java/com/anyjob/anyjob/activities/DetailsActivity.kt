package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.anyjob.anyjob.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    //declare
    private lateinit var etJobTitle: EditText
    private lateinit var etJobDescription: EditText
    private lateinit var etJobBudget: EditText
    private lateinit var btnJobNext: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //initialize variables
        etJobTitle = findViewById(R.id.etJobTitle_d)
        etJobDescription = findViewById(R.id.etJobDescription_d)
        etJobBudget = findViewById(R.id.etJobBudget_d)
        btnJobNext = findViewById(R.id.btnNextDetails_d)

        btnJobNext.setOnClickListener{
            val intent = Intent(this, JobPreviewActivity::class.java)

            intent.putExtra("title", etJobTitle.text.toString())
            intent.putExtra("description", etJobDescription.text.toString())
            intent.putExtra("budget", etJobBudget.text.toString())

            startActivity(intent)
        }




    }
}