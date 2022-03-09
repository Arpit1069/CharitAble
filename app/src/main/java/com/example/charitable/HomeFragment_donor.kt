package com.example.charitable

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_selection.*
import kotlinx.android.synthetic.main.fragment_home_donor.*

class HomeFragment_donor : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        books_button.setOnClickListener {
            val intent = Intent (this@HomeFragment_donor.requireContext(),books_one::class.java)
            startActivity(intent)
        }
        clothes_button.setOnClickListener {
            val intent = Intent (this@HomeFragment_donor.requireContext(),clothes_one::class.java)
            startActivity(intent)
        }
        return inflater.inflate(R.layout.fragment_home_donor, container, false)

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment_donor().apply {
                arguments = Bundle().apply {}

                }

            }
    }
