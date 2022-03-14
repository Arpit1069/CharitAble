package com.example.charitable

import android.app.FragmentTransaction
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.charitable.firebase.FirestoreClass
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_orderdetails_ngo.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment_donor : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile,container,false)
        val btnUpdateProfile = v.findViewById<Button>(R.id.updatedonorprofile)
        val btnlogOut = v.findViewById<Button>(R.id.Profile_logOut)

       btnUpdateProfile.setOnClickListener{
            val intent = Intent(this@ProfileFragment_donor.requireContext(),donor_one::class.java)
            startActivity(intent)
        }

        btnlogOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@ProfileFragment_donor.requireContext(),login::class.java)
            startActivity(intent)
        }

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment_donor().apply {
                arguments = Bundle().apply {}
                FirestoreClass().loadUserData2(this)

                }
//        private const val READ_STORAGE_PERMISSION_CODE = 1
//        private const val PICK_IMAGE_REQUEST_CODE = 2
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
        profile_role.text = user.role

    }


    }
