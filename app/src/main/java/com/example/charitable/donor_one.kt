package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.User
import com.example.charitable.utils.Constants
import kotlinx.android.synthetic.main.activity_donor_one.*
import kotlinx.android.synthetic.main.activity_selection.*
import kotlinx.android.synthetic.main.login.*

class donor_one :  BaseActivity() {

    private lateinit var mUserDetails:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_one)
        FirestoreClass().loadUserData(this)

        btnsubmitdonor.setOnClickListener {

            val intent = Intent(this@donor_one,splash2::class.java)
            updateUserProfileData()
            startActivity(intent)
            finish()
        }
    }

    private fun updateUserProfileData(){
        val userHashMap = HashMap<String,Any>()
        val name: String = namedonor.text.toString().trim { it <= ' ' }
        val mobile: String = mobiledonor.text.toString().trim { it <= ' ' }
        val address: String = addressdonor.text.toString().trim { it <= ' ' }
        val city: String = citydonor.text.toString().trim{ it<= ' '}

        if(namedonor.text.toString()!= mUserDetails.name){
            userHashMap[Constants.NAME] = namedonor.text.toString()
            validateForm3(name,mobile,address,city)
        }
        if(mobiledonor.text.toString()!= mUserDetails.name.toString()){
            userHashMap[Constants.MOBILE] = mobiledonor.text.toString().toLong()
            validateForm3(name,mobile,address,city)
        }
        if(addressdonor.text.toString()!= mUserDetails.name){
            userHashMap[Constants.ADDRESS] = addressdonor.text.toString()
            userHashMap[Constants.ROLE] = "donor"
            validateForm3(name,mobile,address,city)
        }
        if(citydonor.text.toString()!= mUserDetails.name){
            userHashMap[Constants.CITY] = citydonor.text.toString()
            validateForm3(name,mobile,address,city)

        }

        FirestoreClass().updateUserProfileData(this,userHashMap)

    }

    fun setUserDataInUI(user:User){
        mUserDetails = user


        namedonor.setText(user.name)
        if(user.mobile != 0L){
            mobiledonor.setText(user.mobile.toString())
        }
        if(user.address != ""){
            addressdonor.setText(user.address.toString())
        }
        if(user.city != ""){
            citydonor.setText(user.city.toString())
        }
    }
    fun profileUpdateSuccess(){
//        hideProgressDialog()
        startActivity(Intent(this@donor_one,splash2::class.java))
        finish()
    }


    private fun validateForm3(name : String, mobile: String, address: String, city: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter name.")
                false
            }
            TextUtils.isEmpty(mobile) -> {
                showErrorSnackBar("Please enter mobile number.")
                false
            }
            TextUtils.isEmpty(address) -> {
                showErrorSnackBar("Please enter address.")
                false
            }
            TextUtils.isEmpty(city) -> {
                showErrorSnackBar("Please enter city.")
                false
            }
            else -> {
                true
            }
        }

    }

}
