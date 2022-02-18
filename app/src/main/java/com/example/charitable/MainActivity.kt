package com.example.charitable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.User
import com.example.charitable.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.nav_header_nav_drawer.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirestoreClass().signInUser(this)
    }

   fun updateNavigationUserDetails(user: com.example.charitable.models.User){
       Glide
           .with(this)
           .load(user.image)
           .centerCrop()
           .placeholder(R.drawable.ic_user_place_holder)
           .into(profile_pic)
       user_menu.text = user.name
   }





}