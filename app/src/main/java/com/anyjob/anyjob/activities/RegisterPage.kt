package com.anyjob.anyjob.activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.anyjob.anyjob.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
//import com.anyjob.anyjob.activities

class RegisterPage : AppCompatActivity() {

    //    private lateinit var binding : Register
    private lateinit var  auth: FirebaseAuth

    lateinit var register: Button
    var isAllFieldsChecked = false

    var inputEmail : EditText? = null
    var inputPassword1 : EditText? = null
    var inputPassword2 : EditText? = null

    var inputEmailId : EditText? = null
    var inputPassword1Id : EditText? = null
    var inputPassword2Id : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

//        binding = RegisterPage.inflate(layoutInflater)
//        setContentView(binding.root)

        var alreadyHaveAccount = findViewById<TextView>(R.id.AlreadyHaveAccount)
        alreadyHaveAccount.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
        })

        auth= FirebaseAuth.getInstance()

        inputEmailId = findViewById(R.id.inputEmail)
        inputPassword1Id = findViewById(R.id.inputPassword1)
        inputPassword2Id = findViewById(R.id.inputPassword2)
        register = findViewById(R.id.btnRegister)

        // Register button onClick
        register?.setOnClickListener {
            isAllFieldsChecked = CheckAllFields()
            // the boolean variable turns to be true then
            Log.d(ContentValues.TAG, "Email - ${inputEmailId?.text.toString()}")
            Log.d(ContentValues.TAG, "Email - ${inputPassword1Id?.text.toString()}")
            if (isAllFieldsChecked) {
                auth.createUserWithEmailAndPassword(inputEmailId?.text.toString(),inputPassword1Id?.text.toString()).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(applicationContext,"Successfully registered!",Toast.LENGTH_LONG).show();
                        val i = Intent(this, LoginPage::class.java)
                        startActivity(i)
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        }


    }

    private fun CheckAllFields(): Boolean {
        if (inputEmailId!!.length() == 0) {
            inputEmailId!!.error = "Email is required"
            return false
        }
        if (inputPassword1Id!!.length() == 0) {
            inputPassword1Id!!.error = "Password is required"
            return false
        } else if (inputPassword1Id!!.length() < 5) {
            inputPassword1Id!!.error = "Password must be minimum 5 characters"
            return false
        }

        // after all validation return true.
        return true
    }
}