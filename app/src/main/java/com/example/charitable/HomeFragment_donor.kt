package com.example.charitable

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_selection.*
import kotlinx.android.synthetic.main.fragment_home_donor.*

class HomeFragment_donor : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_home_donor, container, false)
        val btn_food = v.findViewById<ImageButton>(R.id.button_food)
        val btn_books = v.findViewById<ImageButton>(R.id.button_books)
        val btn_clothes = v.findViewById<ImageButton>(R.id.button_clothes)

        btn_food.setOnClickListener {
            val intent = Intent(this@HomeFragment_donor.requireContext(),food_zero::class.java)
            startActivity(intent)
        }
        btn_books.setOnClickListener {

            val intent = Intent(this@HomeFragment_donor.requireContext(), books_one::class.java)
            startActivity(intent)
        }
        btn_clothes.setOnClickListener {
            val intent = Intent(this@HomeFragment_donor.requireContext(), clothes_one::class.java)
            startActivity(intent)
        }
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment_donor().apply {
                arguments = Bundle().apply {}
            }
    }
}




