package com.example.charitable

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_orderdetails_ngo.*
import kotlinx.android.synthetic.main.fragment_home2.*
import java.util.jar.Manifest

class HomeFragment_ngo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home_ngo, container, false)

        val btn_food = v.findViewById<ImageButton>(R.id.button_food_order)
        val btn_books = v.findViewById<ImageButton>(R.id.button_book_order)
        val btn_clothes = v.findViewById<ImageButton>(R.id.button_clothes_order)

//        btn_food.setOnClickListener {
//            val intent = Intent(this@HomeFragment_ngo.requireContext(),food_zero_details::class.java)
//            startActivity(intent)
//        }
        btn_books.setOnClickListener {
            val intent = Intent(this@HomeFragment_ngo.requireContext(), OrderdetailsNGO_books::class.java)
            startActivity(intent)
        }
//        btn_clothes.setOnClickListener {
//            val intent = Intent(this@HomeFragment_ngo.requireContext(), clothes_one_details::class.java)
//            startActivity(intent)
//        }

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment_ngo().apply {
                arguments = Bundle().apply {}
            }
    }
}

