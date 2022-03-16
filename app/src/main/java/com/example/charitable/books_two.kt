package com.example.charitable

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_books_two.*

class books_two : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_two)

        donate.setOnClickListener{
            Toast.makeText(applicationContext,"Successfully send to NGO", Toast.LENGTH_LONG).show()
            val intent = Intent(this@books_two,splash2::class.java)
            startActivity(intent)
            finish()
        }

    }
}
