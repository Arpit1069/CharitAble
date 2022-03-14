package com.example.charitable

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.charitable.databinding.ActivityBaseBinding
import com.example.charitable.models.OrderDetails
import com.example.charitable.models.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import kotlinx.android.synthetic.main.activity_books_one.*
import kotlinx.android.synthetic.main.activity_donor_one.*
import kotlinx.android.synthetic.main.fragment_profile.*

class books_one : BaseActivity() {
    private lateinit var mUserDetails: User
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_one)

        database = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/")
        reference = database.getReference("Users")

        proceedbooks.setOnClickListener {
            sendData()
            val intent = Intent(this@books_one, books_two::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun sendData(){

    val quantityBooks = quantity_books.text.toString().trim()
        val stdClass = standard_books.text.toString().trim()
        val BooksOrderProgress = "Started"

        val userName = "Mugemin"
        val userCity = "Pune"
        val userEmail = "Mugemin.vidhale21@vit.edu"
        val userImage = "https://cdn.myanimelist.net/images/characters/14/349249.jpg"
        val userMobile = "95912649030"
        val userAddress = "Vidhya vaibhav colony, Shegaon Naka, Amravati"

        if (quantityBooks.isNotEmpty() && stdClass.isNotEmpty()){
            val model = OrderDetails(quantityBooks,stdClass,BooksOrderProgress
            ,userName, userCity, userMobile, userAddress,userEmail, userImage )

            val id = reference.push().key
            reference.child(id!!).setValue(model)

            quantity_books.setText(quantityBooks)
            standard_books.setText(stdClass)

        }else{
            Toast.makeText(applicationContext,"All fields are Required", Toast.LENGTH_LONG).show()
        }

    }
}