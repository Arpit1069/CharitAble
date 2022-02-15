package com.example.charitable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.nav_header_nav_drawer.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirestoreClass().signInUser(this)
    }
    fun NavDrawer(user: com.google.firebase.firestore.auth.User){
        FirebaseAuth.getInstance()
        Glide
            .with(this)
            .load(user.image)
            .centerCrop()
            .placeholder(R.drawable.logo)
            .into(profile_pic);
        user_menu.text = user.name
    }
}