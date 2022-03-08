package com.example.charitable.firebase

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.example.charitable.donor_one
import com.example.charitable.donor_two
import com.example.charitable.login
import com.example.charitable.models.User
import com.example.charitable.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.activity_donor_two.*

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

    fun updateUserProfileData(activity: donor_one, userHashMap: HashMap<String,Any>){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .update(userHashMap)
            .addOnSuccessListener {
                Log.i(activity.javaClass.simpleName,"Profile data updated successfully")
                Toast.makeText(activity,"Profile updated successfully",Toast.LENGTH_SHORT).show()
                activity.profileUpdateSuccess()
//                activity.updateNavigationUserDetails(loadUserData())
            }.addOnFailureListener{
                    e ->
                activity.hideProgressDialog()
                Log.e(activity.javaClass.simpleName,"Error while creating aboard",e)
                Toast.makeText(activity,"Error while updating profile",Toast.LENGTH_SHORT).show()
            }

    }

    fun loadUserData(activity: Activity){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId()).get()
            .addOnSuccessListener {document->

                val loggedInUser = document.toObject(User::class.java)!!

//                activity is donor_two{activity.updateNavigationUserDetails(loggedInUser)}

                when(activity){
                    is login -> {
                        activity.signInSuccess(loggedInUser)
                    }
                    is donor_one ->{
                        activity.setUserDataInUI(loggedInUser)
                    }
                     is donor_two ->{

                      activity.updateNavigationUserDetails(loggedInUser)
                     }
                }


            }.addOnFailureListener{
                e ->
                when(activity){
                    is login -> {
                        activity.hideProgressDialog()
                    }
                    is donor_one ->{
                        activity.hideProgressDialog()
                    }
//                    is donor_two ->{
//                        activity.hideProgressDialog()
//                    }
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

private fun donor_one.setUserDataInUI(loggedInUser: User) {

}

private fun donor_one.profileUpdateSuccess() {
    TODO("Not yet implemented")
}

