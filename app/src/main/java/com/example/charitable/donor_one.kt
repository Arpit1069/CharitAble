package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.User
import com.example.charitable.utils.Constants
import kotlinx.android.synthetic.main.activity_donor_one.*
import kotlinx.android.synthetic.main.activity_selection.*

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


        if(namedonor.text.toString()!= mUserDetails.name){
            userHashMap[Constants.NAME] = namedonor.text.toString()

        }
        if(mobiledonor.text.toString()!= mUserDetails.name.toString()){
            userHashMap[Constants.MOBILE] = mobiledonor.text.toString().toLong()

        }
        if(addressdonor.text.toString()!= mUserDetails.name){
            userHashMap[Constants.ADDRESS] = addressdonor.text.toString()
            userHashMap[Constants.ROLE] = "donor"
        }
        if(citydonor.text.toString()!= mUserDetails.name){
            userHashMap[Constants.CITY] = citydonor.text.toString()

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
        hideProgressDialog()
        finish()
    }
}