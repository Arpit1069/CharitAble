package com.example.charitable

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ProfileFragment_donor : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_donor, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment_donor().apply {
                arguments = Bundle().apply {}

                }
            }
    }
