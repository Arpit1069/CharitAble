package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_selection.*

class restaurant_one : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_one)
        imageButton.setOnClickListener {
            val intent = Intent(this@restaurant_one,donor_one::class.java)
            startActivity(intent)
            finish()
        }
    }
}