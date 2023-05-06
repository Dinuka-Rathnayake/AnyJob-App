package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.anyjob.anyjob.R
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth

    lateinit var login: Button
    var isAllFieldsChecked = false

    var inputEmailId : EditText? = null
    var inputPassword1Id : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        var createnewAccount = findViewById<TextView>(R.id.createNewAccountText)

        createnewAccount.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, RegisterPage::class.java))
        })

        auth = FirebaseAuth.getInstance()

        inputEmailId = findViewById(R.id.inputEmail)
        inputPassword1Id = findViewById(R.id.inputPassword)

        login = findViewById(R.id.btnLogin)

        login?.setOnClickListener {

            isAllFieldsChecked = CheckAllFields()

            // the boolean variable turns to be true then
            if (isAllFieldsChecked) {
                login.setVisibility(View.GONE)
                auth.signInWithEmailAndPassword(inputEmailId?.text.toString(), inputPassword1Id?.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            login.setVisibility(View.VISIBLE)
                            Toast.makeText(applicationContext,"Successfully login!",Toast.LENGTH_LONG).show();
//                            val i = Intent(this, InitialPage::class.java)
//                            startActivity(i)
                        }
                    }.addOnFailureListener { exception ->
                        login.setVisibility(View.VISIBLE)
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