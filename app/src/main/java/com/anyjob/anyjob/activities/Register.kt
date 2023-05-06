package com.anyjob.anyjob.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anyjob.anyjob.R
import com.anyjob.anyjob.activities.models.ServiceMdl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    private lateinit var sevType: EditText
    private lateinit var sevName: EditText
    private lateinit var sevDes: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sevType = findViewById(R.id.sevType)
        sevName = findViewById(R.id.sevName)
        sevDes = findViewById(R.id.sevDes)
        btnSaveData = findViewById(R.id.btnSaveData)


        dbRef = FirebaseDatabase.getInstance().getReference("Services")

        btnSaveData.setOnClickListener {
            saveEmployeeData()
        }

    }

    private fun saveEmployeeData() {

        //getting values
        val sevTypeZ = sevType.text.toString()
        val sevNameZ = sevName.text.toString()
        val sevDesZ = sevDes.text.toString()

        if(sevTypeZ.isEmpty() || sevNameZ.isEmpty() || sevDesZ.isEmpty()) {
        if(sevTypeZ.isEmpty()){
            sevType.error = "Please enter service type"
        }
        if(sevNameZ.isEmpty()){
            sevName.error = "Please enter service name"
        }
        if(sevDesZ.isEmpty()){
            sevDes.error = "Please describe your service"
        }

        Toast.makeText(this, "Some areas are not filled", Toast.LENGTH_LONG).show()
        }else {

            val sevId = dbRef.push().key!!
            val employee = ServiceMdl(sevId, sevTypeZ, sevNameZ, sevDesZ)

            dbRef.child(sevId).setValue(employee)
                .addOnCompleteListener {
                    Toast.makeText(this, "Service registered successfully", Toast.LENGTH_LONG)
                        .show()

                    sevType.text.clear()
                    sevName.text.clear()
                    sevDes.text.clear()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

    }




}
