package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.User
import android.os.Bundle
import com.example.charitable.utils.Constants
import kotlinx.android.synthetic.main.activity_restaurant_one.*
import kotlinx.android.synthetic.main.activity_selection.*

class restaurant_one : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_one)
        btnsubmitres.setOnClickListener {
            val intent = Intent(this@restaurant_one,splash3::class.java)
            startActivity(intent)
            finish()
        }
    }
}
