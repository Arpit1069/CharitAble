package com.example.charitable

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*

class login : BaseActivity() {


    private lateinit var auth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        auth = FirebaseAuth.getInstance()
        Signup.setOnClickListener {
            Signup.background = resources.getDrawable(R.drawable.switch_trcks,null)
            Signup.setTextColor(resources.getColor(R.color.textColor,null))
            logIn.background=null
            signupLayout.visibility= View.VISIBLE
            logInLayout.visibility= View.GONE
            btnsignup.visibility= View.VISIBLE
            btnlogin.visibility= View.GONE
            logIn.setTextColor(resources.getColor(R.color.bluecolor,null))
        }
        logIn.setOnClickListener {
            Signup.background = null
            Signup.setTextColor(resources.getColor(R.color.teal_200,null))
            logIn.background=resources.getDrawable(R.drawable.switch_trcks,null)
            signupLayout.visibility= View.GONE
            logInLayout.visibility= View.VISIBLE
            btnsignup.visibility= View.GONE
            btnlogin.visibility= View.VISIBLE
            logIn.setTextColor(resources.getColor(R.color.textColor,null))
        }
        btnlogin.setOnClickListener {
            startActivity(Intent(this@login, splash2::class.java))
            btnsignup.setOnClickListener {
                startActivity(Intent(this@login, splash2::class.java))
            }
            signInRegisteredUser()

        }


       btnsignup.setOnClickListener{
            registerUser()
        }

        }

    private fun registerUser() {
        // Here we get the text from editText and trim the space
        val name: String = user_name.text.toString().trim { it <= ' ' }
        val email: String = eMail2.text.toString().trim { it <= ' ' }
        val password: String = Passwords2.text.toString().trim { it <= ' ' }

        if (validateForm(name, email, password)) {
            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                hideProgressDialog()
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    Toast.makeText(
                        this,
                        "$name you have successfully registered the email address $registeredEmail. ",
                        Toast.LENGTH_LONG
                    ).show()
                    FirebaseAuth.getInstance().signOut()
                    finish()
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }


    private fun validateForm(name : String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter name.")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password.")
                false
            }
            else -> {
                true
            }
        }

    }
     private fun signInRegisteredUser(){
         val email: String = eMail.text.toString().trim { it <= ' ' }
         val password: String = Passwords.text.toString().trim { it <= ' ' }

         if (validateForm2(email, password)) {
             showProgressDialog(resources.getString(R.string.please_wait))
             auth.signInWithEmailAndPassword(email, password)
                 .addOnCompleteListener(this) { task ->
                     hideProgressDialog()
                     if (task.isSuccessful) {
                         // Sign in success, update UI with the signed-in user's information
                         Log.d("sign in", "signInWithEmail:success")
                         val user = auth.currentUser
                         startActivity(Intent(this, MainActivity::class.java))
                     } else {
                         // If sign in fails, display a message to the user.
                         Log.w("sign in", "signInWithEmail:failure", task.exception)
                         Toast.makeText(baseContext, "Authentication failed.",
                             Toast.LENGTH_SHORT).show()

                     }
                 }
         }
     }

    private fun validateForm2(email: String, password: String): Boolean {
        return when {

            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password.")
                false
            }
            else -> {
                true
            }
        }

    }
    }

