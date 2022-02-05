package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splash1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash1)
       val time : Long = 2500
        Handler().postDelayed(Runnable {
            val intent =Intent(this@splash1,login::class.java)
            startActivity(intent)
            finish()
        }, time)

    }
}