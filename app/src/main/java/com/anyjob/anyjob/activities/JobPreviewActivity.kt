package com.anyjob.anyjob.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.anyjob.anyjob.R
import com.anyjob.anyjob.models.JobModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class JobPreviewActivity : AppCompatActivity() {
    //declare
    private lateinit var tvJobTitle: TextView
    private lateinit var tvJobDescription : TextView
    private lateinit var tvJobBudget : TextView
    private lateinit var btnJobpost : Button


    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_preview)

        //initialize
        tvJobTitle = findViewById(R.id.tvTitle)
        tvJobDescription = findViewById(R.id.tvDescription)
        tvJobBudget = findViewById(R.id.tvBudget)
        btnJobpost = findViewById(R.id.btnJobPost)

        setValuesToViews()

        //initialize firebase path
        dbRef = FirebaseDatabase.getInstance().getReference("Jobs")

        //set button click
        btnJobpost.setOnClickListener{
            saveJobData()
        }
    }

    private fun setValuesToViews() {
        tvJobTitle.text = intent.getStringExtra("title")
        tvJobDescription.text = intent.getStringExtra("description")
        tvJobBudget.text = intent.getStringExtra("budget")


    }

    private fun saveJobData() {
        //getting values from ui
        val jobTitle = tvJobTitle.text.toString()
        val jobDescription = tvJobDescription.text.toString()
        val jobBudget = tvJobBudget.text.toString()

        if(jobTitle.isEmpty()){
            tvJobTitle.error = "please enter title"
        }
        if(jobDescription.isEmpty()){
            tvJobDescription.error = "please enter description"
        }
        if(jobBudget.isEmpty()){
            tvJobBudget.error = "please enter a fee for job"
        }

        //send data to db
        val jobId = dbRef.push().key!!

        //create object from model
        val job = JobModel(jobId, jobTitle, jobBudget, jobDescription)

        //send data to db
        dbRef.child(jobId).setValue(job)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}