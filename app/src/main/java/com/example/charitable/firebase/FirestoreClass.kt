package com.example.charitable.firebase

import android.app.Activity
import com.example.charitable.login
import com.example.charitable.models.User
import com.example.charitable.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
//import com.google.firebase.firestore.auth.User

class FirestoreClass {
    private val mFireStore = FirebaseFirestore.getInstance()
    fun registerUser(activity: login, userInfo: com.example.charitable.models.User){
    mFireStore.collection(Constants.USERS)
        .document(getCurrentUserId()).set(userInfo, SetOptions.merge())
        .addOnSuccessListener {
            activity.userRegisteredSuccess()
        }
    }

    fun signInUser(activity: Activity){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId()).get()
            .addOnSuccessListener {document->

             val loggedInUser = document.toObject(User::class.java)!!

                when(activity){
                    is login -> {
                        activity.signInSuccess(loggedInUser)
                    }
                // is nav_drawer ->{
                   //  activity.updateNavigationUserDetails(loggedInUser)
                   // }
                }


            }
    }

    fun getCurrentUserId(): String{
        var currentUser = FirebaseAuth.getInstance().currentUser
       var currentUserID = ""
        if(currentUser!=null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }}

