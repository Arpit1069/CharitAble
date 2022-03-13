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
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_home2.*
import java.util.jar.Manifest

class HomeFragment_ngo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home_ngo, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment_ngo().apply {
                arguments = Bundle().apply {}
            }
    }
}

