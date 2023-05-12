package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.anyjob.anyjob.R
import com.anyjob.anyjob.models.JobModel
import com.google.firebase.database.FirebaseDatabase

class MyJobDetailsActivity : AppCompatActivity() {

    private lateinit var tvJobId: TextView
    private lateinit var tvJobTitle: TextView
    private lateinit var tvJobDescription: TextView
    private lateinit var tvJobFee: TextView
    private lateinit var tvJobLocation: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_job_details)

        //recycleview onclick
        initView()
        setValuesToViews()


        //update button
        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("jobId").toString(),
                intent.getStringExtra("jobTitle").toString()

            )
        }

        //delete button
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("jobId").toString()
            )
        }
    }

    private fun initView() {
        tvJobId = findViewById(R.id.tvJobId)
        tvJobTitle = findViewById(R.id.tvJobTitle)
        tvJobDescription = findViewById(R.id.tvJobDescription)
        tvJobFee = findViewById(R.id.tvJobFee)
        tvJobLocation = findViewById(R.id.tvLocation)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvJobId.text = intent.getStringExtra("jobId")
        tvJobTitle.text = intent.getStringExtra("jobTitle")
        tvJobDescription.text = intent.getStringExtra("jobDescription")
        tvJobFee.text = intent.getStringExtra("jobBudget")
        tvJobLocation.text = intent.getStringExtra("jobLocation")
    }

    //delete function
    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Jobs").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Job data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MyJobsActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    //update
    private  fun openUpdateDialog(
        jobId: String,
        jobTitle: String
    ){
        //create inflator window
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etJobTitle = mDialogView.findViewById<EditText>(R.id.etJobTitleUpdate)
        val etJobDescription = mDialogView.findViewById<EditText>(R.id.etJobDescriptionUpdate)
        val etJobFee = mDialogView.findViewById<EditText>(R.id.etJobFeeUpdate)
        val etJobLoacation = mDialogView.findViewById<EditText>(R.id.etJobLocationUpdate)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnJobUpdateData)


        etJobTitle.setText(intent.getStringExtra("jobTitle").toString())
        etJobDescription.setText(intent.getStringExtra("jobDescription").toString())
        etJobFee.setText(intent.getStringExtra("jobBudget").toString())
        etJobLoacation.setText(intent.getStringExtra("jobLocation").toString())

        mDialog.setTitle("Updating $jobTitle Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateJobData(
                jobId,
                etJobTitle.text.toString(),
                etJobDescription.text.toString(),
                etJobFee.text.toString(),
                etJobLoacation.text.toString()
            )

            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvJobTitle.text = etJobTitle.text.toString()
            tvJobDescription.text = etJobDescription.text.toString()
            tvJobFee.text = etJobFee.text.toString()

            alertDialog.dismiss()
        }
    }
    //update function
    private fun updateJobData(
        id: String,
        title: String,
        description: String,
        fee: String,
        location: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Jobs").child(id)
        val empInfo = JobModel(id, title, description, fee, location)
        dbRef.setValue(empInfo)
    }


}