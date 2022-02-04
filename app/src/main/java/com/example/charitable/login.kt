package com.example.charitable

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.login.*

class login : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        Signup.setOnClickListener {
            Signup.background = resources.getDrawable(R.drawable.switch_trcks,null)
            Signup.setTextColor(resources.getColor(R.color.textColor,null))
            logIn.background=null
            signupLayout.visibility= View.VISIBLE
            logInLayout.visibility= View.GONE
            logIn.setTextColor(resources.getColor(R.color.bluecolor,null))
        }
        logIn.setOnClickListener {
            Signup.background = null
            Signup.setTextColor(resources.getColor(R.color.teal_200,null))
            logIn.background=resources.getDrawable(R.drawable.switch_trcks,null)
            signupLayout.visibility= View.GONE
            logInLayout.visibility= View.VISIBLE
            logIn.setTextColor(resources.getColor(R.color. textColor,null))
        }
        signupLayout.setOnClickListener {
            startActivity(Intent(this@login,splash2::class.java))
        }
        }

    }

