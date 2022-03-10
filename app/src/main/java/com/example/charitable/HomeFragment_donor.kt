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
//        books_button.setOnClickListener {
////            val intent = Intent (getActivity(),books_one::class.java)
////            getActivity().startActivity(intent)
//            val intent = Intent (getActivity(), books_one::class.java)
//            getActivity()?.startActivity(intent)
//
//        }
//        clothes_button.setOnClickListener {
//            val intent = Intent (donor_two(),clothes_one::class.java)
//            donor_two().startActivity(intent)
//        }
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
