package com.example.charitablengo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splash4 : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash4)
        val time : Long = 2500
        Handler().postDelayed(Runnable {
        val intent = Intent(this@splash4,ngo_two::class.java)
        startActivity(intent)
        finish()
    },time)

    }
}
