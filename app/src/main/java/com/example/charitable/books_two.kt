package com.example.charitable

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_books_two.*

class books_two : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_two)

        donate.setOnClickListener{
            val intent = Intent(this@books_two,OrderdetailsNGO_books::class.java)
            startActivity(intent)
//            val intent = Intent(this@books_two,donor_one::class.java)
//            startActivity(intent)
            finish()
        }

    }
}
