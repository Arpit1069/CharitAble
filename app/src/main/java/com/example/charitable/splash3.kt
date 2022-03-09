package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splash3 : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash3)
        val time : Long = 2500
        Handler().postDelayed(Runnable {
//        val intent = Intent(this@splash3,restaurant_two::class.java)
        startActivity(Intent(this@splash3,restaurant_two::class.java))
        finish()
    },time)

    }
}