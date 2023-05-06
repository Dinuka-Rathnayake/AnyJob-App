package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.anyjob.anyjob.R
import com.google.firebase.ktx.Firebase

class RegisterPage : AppCompatActivity() {

//    private lateinit var binding = RegisterPage
    private lateinit var  auth: Firebase

    var register: Button? = null
    var isAllFieldsChecked = false

    var inputEmail : EditText? = null
    var inputPassword1 : EditText? = null
    var inputPassword2 : EditText? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

//        binding = RegisterPage

        var alreadyHaveAccount = findViewById<TextView>(R.id.AlreadyHaveAccount)
        alreadyHaveAccount.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
        })


        register = findViewById(R.id.btnRegister)

        // Register button onClick
//        register?.setOnClickListener {
////            MyService.hideKeyboard((register)!!) // hide the keyboard
//            isAllFieldsChecked = CheckAllFields()
//            // the boolean variable turns to be true then
//            if (isAllFieldsChecked) {
//                auth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener { task ->
//                    if(task.isSuccessful){
//                        MyService.createUser(username.text.toString(), email.text.toString(), password.text.toString(), uni.text.toString(), phone.text.toString());
//                        Toast.makeText(applicationContext,"Successfully registered!",Toast.LENGTH_LONG).show();
//                        val i = Intent(this, LoginUser::class.java)
//                        startActivity(i)
//                    }
//                }.addOnFailureListener { exception ->
//                    Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
//                }
//               }
//           }


    }

//    private fun CheckAllFields(): Boolean {
//        if (usernameId!!.length() == 0) {
//            usernameId!!.error = "This field is required"
//            return false
//        }
//        if (emailId!!.length() == 0) {
//            emailId!!.error = "Email is required"
//            return false
//        }
//        if (passwordId!!.length() == 0) {
//            passwordId!!.error = "Password is required"
//            return false
//        } else if (passwordId!!.length() < 5) {
//            passwordId!!.error = "Password must be minimum 5 characters"
//            return false
//        }
//
//        // after all validation return true.
//        return true
//        }
}