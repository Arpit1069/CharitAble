package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_books_two.*
import kotlinx.android.synthetic.main.activity_orderdetails_ngo.*

class books_two : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_two)

        donate.setOnClickListener{
            val intent = Intent(this@books_two,OrderdetailsNGO::class.java)
            startActivity(intent)
//            val intent = Intent(this@books_two,donor_one::class.java)
//            startActivity(intent)
            finish()
        }

    }
}
