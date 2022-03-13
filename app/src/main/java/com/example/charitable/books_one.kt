package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_books_one.*

class books_one : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_one)
        proceedbooks.setOnClickListener {
            val intent = Intent(this@books_one, books_two::class.java)
            startActivity(intent)
            finish()
        }
    }
}