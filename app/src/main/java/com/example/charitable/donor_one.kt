package com.example.charitable

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.MimeTypeFilter
import com.bumptech.glide.Glide
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.User
import com.example.charitable.utils.Constants
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_donor_one.*
import kotlinx.android.synthetic.main.activity_selection.*
import kotlinx.android.synthetic.main.login.*
import java.io.IOException
import java.lang.Exception

class donor_one :  BaseActivity() {

    private lateinit var mUserDetails: User
    private var mSelectedImageFileUri: Uri? = null
    private var mProfileImageURL : String = ""

    companion object{
        const val READ_STORAGE_PERMISSION_CODE = 1
        private const val PICK_IMAGE_REQUEST_CODE = 2
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_one)
        FirestoreClass().loadUserData(this)


        profileUpdateImageDonor.setOnClickListener{
            if(ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_STORAGE_PERMISSION_CODE
                )
            }else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_STORAGE_PERMISSION_CODE
                )
            }
        }


        btnsubmitdonor.setOnClickListener {

            if(mSelectedImageFileUri != null){
                uploadUserImage()
            }

            try {
                updateUserProfileDataDonor()
            }finally{

                val intent = Intent(this@donor_one,com.example.charitable.splash2::class.java)
                startActivity(intent)
                finish()

            }

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == READ_STORAGE_PERMISSION_CODE){
            if (grantResults.isNotEmpty()&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showImageChooser()
            }else{
                Toast.makeText(
                    this,
                    "permission denied", Toast.LENGTH_LONG
                ).show()
            }
        }
    }
        private fun showImageChooser(){
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode== PICK_IMAGE_REQUEST_CODE){
            mSelectedImageFileUri = data?.data
            try{
                Glide
                    .with(this)
                    .load(mSelectedImageFileUri)
                    .centerCrop()
                    .placeholder(R.drawable.ic_person)
                    .into(profileUpdateImageDonor)}catch (e: IOException){
                e.printStackTrace()
            }
        }
    }

    private fun uploadUserImage(){
        showProgressDialog(resources.getString(R.string.please_wait))
        if(mSelectedImageFileUri != null){
            val sRef : StorageReference = FirebaseStorage.getInstance().reference.child("USER_IMAGE" +
                    System.currentTimeMillis() + "." + getFileExtension(mSelectedImageFileUri))
            sRef.putFile(mSelectedImageFileUri!!).addOnSuccessListener {
                taskSnapshot ->
                Log.e(
                    "Firebase Image URL", taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
                )
                taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                    uri ->
                    Log.i("Downloadable Image URl",uri.toString())
                    mProfileImageURL = uri.toString()
                    hideProgressDialog()
                    updateUserProfileDataDonor()

                }

            }.addOnFailureListener{
                exception ->
                Toast.makeText(this@donor_one,exception.message,Toast.LENGTH_LONG).show()
                hideProgressDialog()
            }
        }
    }

    private fun getFileExtension(uri: Uri?): String?{
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri!!))
    }


    private fun updateUserProfileDataDonor(){

        val userHashMap = HashMap<String,Any>()
        val currentUserID = FirestoreClass().getCurrentUserId().toString()

       if(mProfileImageURL.isNotEmpty() && mProfileImageURL != mUserDetails.image){
           userHashMap[Constants.IMAGE] = mProfileImageURL
       }

        if(namedonor.text.toString()!= mUserDetails.name){
            userHashMap[Constants.NAME] = namedonor.text.toString()

        }
        if(mobiledonor.text.toString()!= mUserDetails.name.toString()){
            userHashMap[Constants.MOBILE] = mobiledonor.text.toString().toLong()


        }
        if(addressdonor.text.toString()!= mUserDetails.name){
            userHashMap[Constants.ADDRESS] = addressdonor.text.toString()

            userHashMap[Constants.ROLE] = "Donor"

        }
        if(citydonor.text.toString()!= mUserDetails.name){
            userHashMap[Constants.CITY] = citydonor.text.toString()


        }

        FirestoreClass().updateUserProfileDataDonor(this,userHashMap)

    }

    fun setUserDataInUIDonor(user:User){
        mUserDetails = user

        namedonor.setText(user.name)
        if(user.mobile != 0L){
            mobiledonor.setText(user.mobile.toString())
        }
        if(user.address != ""){
            addressdonor.setText(user.address.toString())
        }
        if(user.city != ""){
            citydonor.setText(user.city.toString())
        }
    }

    fun profileUpdateSuccessDonor(){
        startActivity(Intent(this@donor_one,splash2::class.java))
        finish()
    }

}
