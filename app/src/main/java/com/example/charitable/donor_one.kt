package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_donor_one.*
import kotlinx.android.synthetic.main.activity_selection.*

class donor_one :  BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_one)
        btnsubmitdonor.setOnClickListener {
            val intent = Intent(this@donor_one,splash2::class.java)
            startActivity(intent)
            finish()
        }
    }
}