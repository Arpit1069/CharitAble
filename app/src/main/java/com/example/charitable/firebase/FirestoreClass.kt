package com.example.charitable.firebase

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.charitable.*
import com.example.charitable.models.User
import com.example.charitable.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {
    private val mFireStore = FirebaseFirestore.getInstance()
    fun registerUser(activity: login, userInfo: com.example.charitable.models.User) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId()).set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }
    }

    fun updateUserProfileDataDonor(activity: donor_one, userHashMap: HashMap<String, Any>) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .update(userHashMap)
            .addOnSuccessListener {
                Log.i(activity.javaClass.simpleName, "Profile data updated successfully")
                Toast.makeText(activity, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                activity.profileUpdateSuccessDonor()

            }.addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(activity.javaClass.simpleName, "Error while creating aboard", e)
                Toast.makeText(activity, "Error while updating profile", Toast.LENGTH_SHORT).show()
            }

    }


    fun updateUserProfileDatango(
        activity: ngo_one,
        userHashMapTwo: HashMap<String, Any>
    ) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .update(userHashMapTwo)
            .addOnSuccessListener {
                Log.i(activity.javaClass.simpleName, "Profile data updated successfully")
                Toast.makeText(activity, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                activity.profileUpdateSuccessngo()

            }.addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(activity.javaClass.simpleName, "Error while creating aboard", e)
                Toast.makeText(activity, "Error while updating profile", Toast.LENGTH_SHORT).show()
            }

    }


    fun loadUserData(activity: Activity) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId()).get()
            .addOnSuccessListener { document ->

                val loggedInUser = document.toObject(User::class.java)

                when (activity) {
                    is login -> {
                        if (loggedInUser != null) {
                            activity.signInSuccess(loggedInUser)
                        }
                    }
                    is donor_one -> {
                        if (loggedInUser != null) {
                            activity.setUserDataInUIDonor(loggedInUser)
                        }
                    }
                    is ngo_one -> {

                        if (loggedInUser != null) {
                            activity.setUserDataInUIngo(loggedInUser)
                        }
                    }
//                    is books_one -> {
//                        if( loggedInUser != null){
//                            activity.sendData(loggedInUser)
//                        }
//                    }
                }


            }.addOnFailureListener { e ->
                when (activity) {
                    is login -> {
                        activity.hideProgressDialog()
                    }
                    is donor_one -> {
                        activity.hideProgressDialog()
                    }
                    is donor_two ->{
                        activity.hideProgressDialog()
                    }
                }

            }

    }

    fun loadUserData2(fragment: Fragment) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId()).get()
            .addOnSuccessListener { document ->

                val loggedInUser = document.toObject(User::class.java)

                when (fragment) {
                    is ProfileFragment_donor -> {
                        if (loggedInUser != null) {
                            fragment.updateNavigationUserDetails(loggedInUser)
                        }
                    }
                }
            }
    }
    fun loadUserData3(fragment: Fragment) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId()).get()
            .addOnSuccessListener { document ->

                val loggedInUser = document.toObject(User::class.java)

                when (fragment) {
                    is ProfileFragment_res -> {
                        if (loggedInUser != null) {
                            fragment.updateNavigationUserDetails2(loggedInUser)
                        }
                    }
                }
            }
    }

    fun getCurrentUserId(): String {
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    private fun donor_one.setUserDataInUI(loggedInUser: User) {

    }

    private fun donor_one.profileUpdateSuccess() {
        TODO("Not yet implemented")
    }
}
