package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_clothes_one.*
import kotlinx.android.synthetic.main.activity_food_zero.*

class food_zero : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_zero)
        resbtn.setOnClickListener {
            val intent = Intent(this@food_zero,food_one::class.java)
            startActivity(intent)
            finish()
        }
    }
}