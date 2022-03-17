package com.example.charitable

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.OrderDetails_books
import com.example.charitable.models.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_books_one.*

class books_one : BaseActivity() {

private var selectedItemIndex = 0
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_one)
        FirestoreClass().loadUserData(this)
        database = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/")
        reference = database.getReference("Users")

        proceedbooks.setOnClickListener {
            sendData(User())
            val intent = Intent(this@books_one, books_two::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun showConfirmationDialog(view: View){

        val NGO_books = arrayOf("None","NGO ka kaam","Taki","Anime","Waifu","Link do re","Link provider","Dead")

        var selectedNGO_books = NGO_books[selectedItemIndex]

        MaterialAlertDialogBuilder(this)
            .setTitle("NGOs For Books")
            .setSingleChoiceItems(NGO_books, selectedItemIndex){ dialog, which ->
                selectedItemIndex = which
                selectedNGO_books = NGO_books[which]

            }
            .setPositiveButton("OK") { dialog, which ->

                Toast.makeText(applicationContext,"$selectedNGO_books Selected", Toast.LENGTH_LONG).show()

            }
            .setNeutralButton("Cancel") { dialog, which ->
            }
            .show()

    }

   // private
    fun sendData(user: User) {

    val quantityBooks = quantity_books.text.toString().trim()
        val stdClass = standard_books.text.toString().trim()
        val BooksOrderProgress = "InProgress"

//       reference.child("Users").child(userId).child("username").setValue(name)
        if (quantityBooks.isNotEmpty() && stdClass.isNotEmpty()){

            val model = OrderDetails_books(quantityBooks,stdClass,BooksOrderProgress)

            val currentUserID = FirestoreClass().getCurrentUserId()


            reference.child(currentUserID).setValue(model)

            quantity_books.setText(quantityBooks)
            standard_books.setText(stdClass)
            Toast.makeText(applicationContext,"Successfully send to NGO", Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(applicationContext,"All fields are Required", Toast.LENGTH_LONG).show()
        }

    }
}