package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ngo_one.*

class ngo_one : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_one)
        btnsubmitngo.setOnClickListener {
            val intent = Intent(this@ngo_one,splash4::class.java)
            startActivity(intent)
            finish()

        }
    }
}