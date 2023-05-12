package com.anyjob.anyjob.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.anyjob.anyjob.R

class AllJobDetailsActivity : AppCompatActivity() {

    private lateinit var tvJobId: TextView
    private lateinit var tvJobTitle: TextView
    private lateinit var tvJobDescription: TextView
    private lateinit var tvJobFee: TextView
    private lateinit var tvJobLocation: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_job_details)

        //recycleview onclick
        initView()
        setValuesToViews()
    }

    private fun initView() {
        tvJobId = findViewById(R.id.tvJobId)
        tvJobTitle = findViewById(R.id.tvJobTitle)
        tvJobDescription = findViewById(R.id.tvJobDescription)
        tvJobFee = findViewById(R.id.tvJobFee)
        tvJobLocation = findViewById(R.id.tvLocation)

    }

    private fun setValuesToViews() {
        tvJobId.text = intent.getStringExtra("jobId")
        tvJobTitle.text = intent.getStringExtra("jobTitle")
        tvJobDescription.text = intent.getStringExtra("jobDescription")
        tvJobFee.text = intent.getStringExtra("jobBudget")
        tvJobLocation.text = intent.getStringExtra("jobLocation")
    }
}