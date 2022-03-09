package com.example.charitable

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.charitable.firebase.FirestoreClass
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment_donor : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment_donor().apply {
                arguments = Bundle().apply {}
                FirestoreClass().loadUserData2(this)

                }
            }

    fun updateNavigationUserDetails(user: com.example.charitable.models.User){

        Glide
            .with(this)
            .load(user.image)
            .centerCrop()
            .placeholder(R.drawable.ic_person)
           .into(profile_image);
       profile_name.text = user.name
        address_donor.text = user.address
        number_donor.text = user.mobile.toString()
        email_donor.text = user.email

    }


    }
