package com.example.charitable.firebase

import com.example.charitable.login
import com.example.charitable.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User

class FirestoreClass {
    private val mFireStore = FirebaseFirestore.getInstance()
    fun registerUser(activity: login, userInfo: com.example.charitable.models.User){
    mFireStore.collection(Constants.USERS)
        .document(getCurrentUserId()).set(userInfo, SetOptions.merge())
        .addOnSuccessListener {
            activity.userRegisteredSuccess()
        }
    }

    fun signInUser(activity: login){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId()).get()
            .addOnSuccessListener {document->
               val loggedInUser = document.toObject(User::class.java)!!
               if(loggedInUser!=null)
                activity.signInSuccess(loggedInUser)
            }
    }

    fun getCurrentUserId(): String{
        var currentUser = FirebaseAuth.getInstance().currentUser
       var currentUserID = ""
        if(currentUser!=null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
}