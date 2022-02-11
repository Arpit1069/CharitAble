package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class pgdonor : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pgdonor)
        val intent = Intent(this@pgdonor,splash2::class.java)
        startActivity(intent)
        finish()
    }
}