package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.User
import android.os.Bundle
import com.example.charitable.utils.Constants
import kotlinx.android.synthetic.main.activity_donor_one.*
import kotlinx.android.synthetic.main.activity_restaurant_one.*
import kotlinx.android.synthetic.main.activity_selection.*

class restaurant_one : BaseActivity() {
    private lateinit var mUserDetails:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_one)
        FirestoreClass().loadUserData(this)
        btnsubmitres.setOnClickListener {
            val intent = Intent(this@restaurant_one,splash3::class.java)
            updateUserProfileDataRestaurant()
            startActivity(intent)
            finish()
        }
    }

    private fun updateUserProfileDataRestaurant(){
        val userHashMapTwo = HashMap<String,Any>()
//        val name: String = namedonor.text.toString().trim { it <= ' ' }
//        val mobile: String = mobiledonor.text.toString().trim { it <= ' ' }
//        val address: String = addressdonor.text.toString().trim { it <= ' ' }
//        val city: String = citydonor.text.toString().trim{ it<= ' '}

        if(nameres.text.toString()!= mUserDetails.name){
            userHashMapTwo[Constants.NAME] = nameres.text.toString()

        }
        if(mobileres.text.toString()!= mUserDetails.name.toString()){
            userHashMapTwo[Constants.MOBILE] = mobileres.text.toString().toLong()

        }
        if(addressres.text.toString()!= mUserDetails.name){
            userHashMapTwo[Constants.ADDRESS] = addressres.text.toString()
            userHashMapTwo[Constants.ROLE] = "Restaurant"

        }
        if(cityres.text.toString()!= mUserDetails.name){
            userHashMapTwo[Constants.CITY] = cityres.text.toString()

        }

        FirestoreClass().updateUserProfileDataRestaurant(this,userHashMapTwo)

    }

    fun setUserDataInUIRestaurant(user:User){
        mUserDetails = user

        nameres.setText(user.name)
        if(user.mobile != 0L){
            mobileres.setText(user.mobile.toString())
        }
        if(user.address != ""){
            addressres.setText(user.address.toString())
        }
        if(user.city != ""){
            cityres.setText(user.city.toString())
        }
    }

    fun profileUpdateSuccessRestaurant(){
//        hideProgressDialog()
        startActivity(Intent(this@restaurant_one,splash3::class.java))
        finish()
    }


}
