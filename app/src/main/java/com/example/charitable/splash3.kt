package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class splash3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash3)
        val time : Long = 2500
        val intent = Intent(this@splash3,restaurant::class.java)
        startActivity(intent)
        finish()
    }, time)
}


    }
}