package com.example.charitable

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.OrderDetails_books
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_books_one.*

class books_one : BaseActivity() {

private var selectedItemIndex = 0
    private var selectedNGO_books = ""
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_one)
        FirestoreClass().loadUserData(this)
        database = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/")
        reference = database.getReference("BooksOrder")

        proceedbooks.setOnClickListener {
            sendData()

        }

    }


    fun showConfirmationDialog(view: View){

        val NGO_books = arrayOf("NGO1","NGO2",
            "NGO3","NGO4","NGO5",
            "NGO6","NGO7",
            "NGO8","NGO8",
        "NGO9", "NGO10")

        selectedNGO_books = NGO_books[selectedItemIndex]

        MaterialAlertDialogBuilder(this)
            .setTitle("NGOs For Books")
            .setSingleChoiceItems(NGO_books, selectedItemIndex){ dialog, which ->
                selectedItemIndex = which
                selectedNGO_books = NGO_books[which]

            }
            .setPositiveButton("OK") { dialog, which ->

                Toast.makeText(applicationContext,"$selectedNGO_books Selected", Toast.LENGTH_LONG).show()
                select_ngo.setText("NGO Selected")

            }
            .setNeutralButton("Cancel") { dialog, which ->
            }
            .show()
    }

    fun sendData() {

        val nameBooks = name_books.text.toString()
        val mobileBooks = mobile_no_books.text.toString().trim()
        val addressBooks = address_books.text.toString()
        val quantityBooks = quantity_books.text.toString().trim()
        val stdClass = standard_books.text.toString()
        val selectedNGO_here = selectedNGO_books.trim()

        if (quantityBooks.isNotEmpty() && stdClass.isNotEmpty() && nameBooks.isNotEmpty() && addressBooks.isNotEmpty() &&
                mobileBooks.isNotEmpty() && selectedNGO_here.isNotEmpty()){
            if(mobileBooks.length != 10){
                Toast.makeText(this, "INVALID MOBILE NUMBER", Toast.LENGTH_SHORT).show()
            }else{

//            val id = reference.push().key!!

            val id = mobile_no_books.text.toString().trim()
            val model = OrderDetails_books(id,nameBooks,quantityBooks,stdClass, mobileBooks, addressBooks, selectedNGO_here)

            reference.child(id).setValue(model)

            quantity_books.setText(id)
            standard_books.setText(selectedNGO_here)
            Toast.makeText(applicationContext,"Successfully send to NGO", Toast.LENGTH_LONG).show()

            val intent = Intent(this@books_one, books_two::class.java)
            startActivity(intent)
            finish()}

        }else{
            Toast.makeText(applicationContext,"All fields are Required", Toast.LENGTH_LONG).show()
        }

    }
}