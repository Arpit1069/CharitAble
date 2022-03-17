package com.example.charitable

import android.os.Bundle
import android.os.StrictMode
import com.example.charitable.firebase.FirestoreClass


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirestoreClass().loadUserData(this)

    }



}