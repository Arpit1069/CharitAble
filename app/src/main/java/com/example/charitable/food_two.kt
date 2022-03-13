package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_clothes_one.*
import kotlinx.android.synthetic.main.activity_food_two.*

class food_two : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_two)
        proceedfoods_two.setOnClickListener {
            val intent = Intent(this@food_two,food_three::class.java)
            startActivity(intent)
            finish()
        }
    }
}