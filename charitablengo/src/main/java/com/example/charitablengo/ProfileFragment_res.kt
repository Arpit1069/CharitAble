package com.example.charitablengo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bumptech.glide.Glide
import com.example.charitablengo.firebase.FirestoreClass
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment_res : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile,container,false)
        val btnUpdateProfile = v.findViewById<Button>(R.id.updatedonorprofile)
        val btnlogOut = v.findViewById<Button>(R.id.Profile_logOut)
        btnUpdateProfile.setOnClickListener{
            val intent = Intent(this@ProfileFragment_res.requireContext(),ngo_one::class.java)
            startActivity(intent)

        }
        btnlogOut.setOnClickListener{
            val intent = Intent(this@ProfileFragment_res.requireContext(),login::class.java)
            startActivity(intent)

        }

        return v

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment_res().apply {
                arguments = Bundle().apply {}
                FirestoreClass().loadUserData3(this)
            }
    }
    fun updateNavigationUserDetails2(user: com.example.charitablengo.models.User){

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