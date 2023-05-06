package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.anyjob.anyjob.R

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        var createnewAccount = findViewById<TextView>(R.id.createNewAccountText)

        createnewAccount.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, RegisterPage::class.java))
        })
    }
}