package com.example.charitablengo

import android.os.Bundle
import android.os.StrictMode
import com.example.charitablengo.BaseActivity
import com.example.charitablengo.firebase.FirestoreClass


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        FirestoreClass().loadUserData(this)

    }



}