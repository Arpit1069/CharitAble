package com.example.charitable

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.OrderDetails_books
import com.example.charitable.models.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_books_one.*

class books_one : BaseActivity() {
    private lateinit var mUserDetails: User
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

//    lateinit var brief_details_books_email : Array<String>
//    lateinit var brief_details_books_number : Array<String>
//    lateinit var brief_details_books_address : Array<String>
//    lateinit var brief_details_books_city: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_one)
        FirestoreClass().loadUserData(this)
        database = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/")
        reference = database.getReference("Users")

        proceedbooks.setOnClickListener {
            sendData(user = User())
            val intent = Intent(this@books_one, books_two::class.java)
            startActivity(intent)
            finish()
        }
    }

   // private
    fun sendData(user: User) {

    val quantityBooks = quantity_books.text.toString().trim()
        val stdClass = standard_books.text.toString().trim()
        val BooksOrderProgress = "Started"
       mUserDetails = user

//        val userName = user.name
//        val userCity = user.city
//        val userEmail = user.email
//        val userImage = "https://cdn.myanimelist.net/images/characters/14/349249.jpg"
//        val userMobile = "95912649030"
//        val userAddress = "Vidhya vaibhav colony, Shegaon Naka, Amravati"

        if (quantityBooks.isNotEmpty() && stdClass.isNotEmpty()){

            val userName = mUserDetails.name
            val userCity = mUserDetails.city
            val userEmail = mUserDetails.email
            val userImage = "https://cdn.myanimelist.net/images/characters/14/349249.jpg"
            val userMobile = mUserDetails.mobile.toString()
            val userAddress = mUserDetails.address

            val model = OrderDetails_books(quantityBooks,stdClass,BooksOrderProgress
            ,userName, userCity, userMobile, userAddress,userEmail, userImage )

            val id = reference.push().key
            reference.child(id!!).setValue(model)

            quantity_books.setText(quantityBooks)
            standard_books.setText(stdClass)
            Toast.makeText(applicationContext,userEmail.toString(), Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(applicationContext,"All fields are Required", Toast.LENGTH_LONG).show()
        }

    }
}