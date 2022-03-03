package com.example.charitable.models

import android.os.Parcel
import android.os.Parcelable
import com.bumptech.glide.Glide
import com.example.charitable.R
import com.google.firebase.auth.FirebaseAuth
//import kotlinx.android.synthetic.main.nav_header_nav_drawer.*

data class User (
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val image: String = "",
    val mobile: Long = 0,
    val address: String = "",
    val fcmtoken: String ="",
    val role: String = "",
    val city: String = "",

        ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int)= with(dest) {
        writeString(id)
        writeString(name)
        writeString(email)
        writeString(image)
        writeLong(mobile)
        writeString(address)
        writeString(fcmtoken)
        writeString(role)
        writeString(city)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }



}
