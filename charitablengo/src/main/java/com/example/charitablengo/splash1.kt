package com.example.charitablengo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.charitablengo.firebase.FirestoreClass

class splash1 : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash1)
       val time : Long = 2500
        Handler().postDelayed(Runnable {

            val currentUserID = FirestoreClass().getCurrentUserId()
            if(currentUserID.isNotEmpty()){
                startActivity(Intent(this@splash1,splash4::class.java))
            }else{
                startActivity(Intent(this@splash1,login::class.java))
            }

//            startActivity(Intent(this@splash1,login::class.java))
            finish()
        }, time)

    }
}