package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_clothes_one.*
import kotlinx.android.synthetic.main.activity_food_one.*

class food_one : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_one)
        add_menu.setOnClickListener {
            val intent = Intent(this@food_one,food_two::class.java)
            startActivity(intent)
            finish()
        }
    }
}