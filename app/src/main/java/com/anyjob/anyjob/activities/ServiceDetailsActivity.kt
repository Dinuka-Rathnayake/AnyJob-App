package com.anyjob.anyjob.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.anyjob.anyjob.R
import com.anyjob.anyjob.activities.models.ServiceMdl
import com.google.firebase.database.FirebaseDatabase

class ServiceDetailsActivity : AppCompatActivity() {

    private lateinit var tvSevId: TextView
    private lateinit var tvSevName: TextView
    private lateinit var tvSevType: TextView
    private lateinit var tvSevDes: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("sevId").toString(),
                intent.getStringExtra("sevType").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("sevId").toString()
            )
        }

    }

    private fun initView() {
        tvSevId = findViewById(R.id.tvSevId)
        tvSevType = findViewById(R.id.tvSevType)
        tvSevName = findViewById(R.id.tvSevName)
        tvSevDes = findViewById(R.id.tvSevDes)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvSevId.text = intent.getStringExtra("sevId")
        tvSevType.text = intent.getStringExtra("sevType")
        tvSevName.text = intent.getStringExtra("sevName")
        tvSevDes.text = intent.getStringExtra("sevDes")

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Services").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Service data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(
        sevId: String,
        sevType: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etSevType = mDialogView.findViewById<EditText>(R.id.etSevType)
        val etSevName = mDialogView.findViewById<EditText>(R.id.etSevName)
        val etSevDes = mDialogView.findViewById<EditText>(R.id.etSevDes)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etSevType.setText(intent.getStringExtra("sevType").toString())
        etSevName.setText(intent.getStringExtra("sevName").toString())
        etSevDes.setText(intent.getStringExtra("sevDes").toString())

        mDialog.setTitle("Updating $sevType Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                sevId,
                etSevType.text.toString(),
                etSevName.text.toString(),
                etSevDes.text.toString()
            )

            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvSevType.text = etSevType.text.toString()
            tvSevName.text = etSevName.text.toString()
            tvSevDes.text = etSevDes.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateEmpData(
        id: String,
        type: String,
        name: String,
        des: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Services").child(id)
        val sevInfo = ServiceMdl(id, type, name, des)
        dbRef.setValue(sevInfo)
    }

}