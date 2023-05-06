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
import com.anyjob.anyjob.models.FeedbackModel
import com.google.firebase.database.FirebaseDatabase

class FeedbackDetailsActivity : AppCompatActivity() {
    private lateinit var tvFeedbackId: TextView
    private lateinit var tvFeedbackEmpName: TextView
    private lateinit var tvFeedback: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_details)

        //recycleview onclick
        initView()
        setValuesToViews()

        //update button
        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("feedbackId").toString(),
                intent.getStringExtra("feedbackEmployeeName").toString()

            )
        }
        //delete button
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("feedbackId").toString()
            )
        }
    }

    private  fun openUpdateDialog(
        feedbackId: String,
        empName: String
    ){
        //create inflator window
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etFeedbackEmpName = mDialogView.findViewById<EditText>(R.id.etFeedbackEmpNameUpdate)
        val etFeedback = mDialogView.findViewById<EditText>(R.id.etFeedbackUpdate)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnFeedbackUpdateData)

        etFeedbackEmpName.setText(intent.getStringExtra("feedbackEmployeeName").toString())
        etFeedback.setText(intent.getStringExtra("cusFeedback").toString())


        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateFeedbackData(
                feedbackId,
                etFeedbackEmpName.text.toString(),
                etFeedback.text.toString(),

            )

            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvFeedbackEmpName.text = etFeedbackEmpName.text.toString()
            tvFeedback.text = etFeedback.text.toString()


            alertDialog.dismiss()
        }
    }

    //update function
    private fun updateFeedbackData(
        id: String,
        name: String,
        feedback: String

    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks").child(id)
        val empInfo = FeedbackModel(id, name, feedback)
        dbRef.setValue(empInfo)
    }

    //delete function
    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Feedback data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MyFeedbacksActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvFeedbackId = findViewById(R.id.tvFeedbackId)
        tvFeedbackEmpName = findViewById(R.id.tvFeedbakEmpName)
        tvFeedback = findViewById(R.id.tvFeedback)


        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvFeedbackId.text = intent.getStringExtra("feedbackId")
        tvFeedbackEmpName.text = intent.getStringExtra("feedbackEmployeeName")
        tvFeedback.text = intent.getStringExtra("cusFeedback")


    }


}