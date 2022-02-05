package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class frontsplash : AppCompatActivity() {

    private val frontsplashtimeout : Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frontsplash)

        Handler()postDelayed({

              startActivity(Intent(this, frontsplash::class.java)
            finish()

        }, frontsplashtimeout())
    }
}

private infix fun Any.finish(unit: Unit): Intent? {

}




