package com.example.charitable

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_books_two.*
import org.w3c.dom.Text

class books_two : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_two)

        donate.setOnClickListener{
            Toast.makeText(applicationContext,"Successfully send to NGO", Toast.LENGTH_LONG).show()
            val intent = Intent(this@books_two,splash2::class.java)
            startActivity(intent)
            finish()
        }

        val selectedNGO_books_inlast = intent.getStringExtra("NGO details")


        NGO_name_details_books.findViewById<TextView>(R.id.NGO_name_details_books).apply {
            text = selectedNGO_books_inlast
        }

        if (selectedNGO_books_inlast == "NGO1" ){
            NGO_location_details_books.setText("good")
            NGO_city_details_books.setText("Pune")
            NGO_mobile_details_books.setText("9872625347")
        }else if (selectedNGO_books_inlast == "NGO2" ){
            NGO_location_details_books.setText("NGO1")
            NGO_city_details_books.setText("Pune")
            NGO_mobile_details_books.setText("1284352315")


        }else if (selectedNGO_books_inlast == "NGO3" ){
            NGO_location_details_books.setText("noice")
            NGO_city_details_books.setText("Mumbai")
            NGO_mobile_details_books.setText("2478964258")


        }else if (selectedNGO_books_inlast == "NGO4" ){

            NGO_location_details_books.setText("watch")
            NGO_city_details_books.setText("New York")
            NGO_mobile_details_books.setText("6535792457")

        }else if (selectedNGO_books_inlast == "NGO5" ){

            NGO_location_details_books.setText("cat")
            NGO_city_details_books.setText("Amravati")
            NGO_mobile_details_books.setText("9085435689")

        }else if (selectedNGO_books_inlast == "NGO6" ){

            NGO_location_details_books.setText("ok ok thike")
            NGO_city_details_books.setText("Wakanda")
            NGO_mobile_details_books.setText("4345673262")

        }else if (selectedNGO_books_inlast == "NGO7" ){

            NGO_location_details_books.setText("Link do re")
            NGO_city_details_books.setText("shiganshina")
            NGO_mobile_details_books.setText("5742467357")

        }else{
            Toast.makeText(this, "Something Went Wrong, Try Again!", Toast.LENGTH_SHORT).show()
        }




    }
}
