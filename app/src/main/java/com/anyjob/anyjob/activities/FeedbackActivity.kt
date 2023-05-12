package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.anyjob.anyjob.R
import com.anyjob.anyjob.models.FeedbackModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FeedbackActivity : AppCompatActivity() {

    //declare ui elements
    private lateinit var etEmpName: EditText
    private lateinit var etCusFeedback: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        //initialize variables

        etEmpName = findViewById(R.id.empName)
        etCusFeedback = findViewById(R.id.feedbackmultiline)
        btnSaveData = findViewById(R.id.savebtn1)

        //initialize firebase path
        dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks")

        //set button
        btnSaveData.setOnClickListener {
            saveFeedbackData()


        }



    }

    private fun saveFeedbackData() {

        //getting values from ui
        val empName = etEmpName.text.toString()
        val cusFeedback = etCusFeedback.text.toString()

        //validations
        if(empName.isEmpty()){
            etEmpName.error = "Please Enter Employee Name"
        }
        if(cusFeedback.isEmpty()){
            etCusFeedback.error ="Please Give Your Feedback"
        }
        else {

            //send data to db
            val jobId = dbRef.push().key!!

            //create object from model
            val feedback = FeedbackModel(jobId, empName, cusFeedback)

            //send data to db
            dbRef.child(jobId).setValue(feedback)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                    etEmpName.text.clear()
                    etCusFeedback.text.clear()

                    val intent = Intent(this, MyFeedbacksActivity::class.java)
                    startActivity(intent)


                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

    }
}