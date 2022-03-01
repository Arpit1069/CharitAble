package com.example.charitable

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.login.*


class login : BaseActivity() {
    private val RC_SIGN_IN = 89
    private lateinit var googleSignInClient:GoogleSignInClient

    private lateinit var auth: FirebaseAuth

    @SuppressLint("UseCompatLoadingForDrawables")
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
        btnlogin.setOnClickListener{
            signInRegisteredUser()
        }

        btnforgotpassword.setOnClickListener{
           val email: String = eMail.text.toString().trim { it <= ' ' }
            auth.signInWithEmailAndPassword(email, null.toString())
            Firebase.auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                       Log.d(TAG, "Email sent.")
                        Toast.makeText(baseContext, "Email sent to $email.",
                            Toast.LENGTH_SHORT).show()

                    }else{
                        showErrorSnackBar("Email not sent .")
                    }
                }
        }

       btnsignup.setOnClickListener{
            registerUser()
        }


        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id_auth))
            .requestEmail()
            .build()

         googleSignInClient = GoogleSignIn.getClient(this, gso)


        }


    fun userRegisteredSuccess(){
        Toast.makeText(
            this,
            "you have successfully registered.",
            Toast.LENGTH_LONG
        ).show()
        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        finish()
    }

    private fun registerUser() {
        // Here we get the text from editText and trim the space
        val name: String = user_name.text.toString().trim { it <= ' ' }
        val email: String = eMail2.text.toString().trim { it <= ' ' }
        val password: String = Passwords2.text.toString().trim { it <= ' ' }
        val password2: String = Passwords3.text.toString().trim{ it<= ' '}
        if (Passwords2.text.toString() != Passwords3.text.toString()){
            Toast.makeText(this,"confirm password does not match with the password", Toast.LENGTH_SHORT).show()
        }else{
        if (validateForm(name, email, password, password2)) {
            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    val user= User(firebaseUser.uid, name,registeredEmail)
                    FirestoreClass().registerUser(this, user)
                    startActivity(Intent(this@login, pgdonor::class.java))

//                    Toast.makeText(
//                        this,
//                        "$name you have successfully registered the email address $registeredEmail. ",
//                        Toast.LENGTH_LONG
//                    ).show()
//                    FirebaseAuth.getInstance().signOut()
//                    finish()
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }}


            }
        }
    }

    fun signInSuccess(user: User){
        hideProgressDialog()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun validateForm(name : String, email: String, password: String, password2: String): Boolean {
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
            TextUtils.isEmpty(password2) -> {
                showErrorSnackBar("Please confirm the password.")
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
                        // startActivity(Intent(this, MainActivity::class.java))
                         startActivity(Intent(this@login, pgdonor::class.java))
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

    fun GoogleSignIn(view: View) {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!

                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("error 90", "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val auth = FirebaseAuth.getInstance()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("error 90", "signInWithCredential:success")
                    val user = auth.currentUser

                    startActivity(Intent(this, pgdonor::class.java))
                    Log.d("error 90","firebaseAuthWithGoogle: ${user?.displayName}")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("error 90", "signInWithCredential:failure", task.exception)

                }
            }
    }



}


