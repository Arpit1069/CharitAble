package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_selection.*

class choose : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
       imageButton.setOnClickListener {
           val intent = Intent(this@choose,donor_one::class.java)
           startActivity(intent)
           finish()
        }
        imageButton6.setOnClickListener {
            val intent = Intent(this@choose,restaurant_one::class.java)
            startActivity(intent)
            finish()
        }
        imageButton7.setOnClickListener {
            val intent = Intent(this@choose,ngo_one::class.java)
            startActivity(intent)
            finish()
        }
    }
}