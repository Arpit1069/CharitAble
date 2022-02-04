package com.example.charitable

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.login.*

open class login : BaseActivity() {
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
       Signup.setOnClickListener{
           registerUser()
       }
        logIn.setOnClickListener {
            Signup.background = null
            Signup.setTextColor(resources.getColor(R.color.teal_200,null))
            logIn.background=resources.getDrawable(R.drawable.switch_trcks,null)
            signupLayout.visibility= View.GONE
            logInLayout.visibility= View.VISIBLE
            logIn.setTextColor(resources.getColor(R.color. textColor,null))
        }
        signIn.setOnClickListener {
            startActivity(Intent(this@login,splash2::class.java))

        }
        }
    private fun registerUser(){
        val email:String= eMail2.text.toString().trim{ it<=' '}
        val password:String= Passwords2.text.toString().trim{ it<=' '}
        if(validateForm(email, password)){
            Toast.makeText(
                this@login,
                "Now we can register a new user",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun validateForm(email:String, password:String):Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Enter your Email")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Enter your Password")
                false
            }
            else->{
                true
            }
        }
        }
    }


