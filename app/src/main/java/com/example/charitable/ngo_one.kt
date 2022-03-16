package com.example.charitable

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.User
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.charitable.utils.Constants
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_donor_one.*
import kotlinx.android.synthetic.main.activity_ngo_one.*
import java.io.IOException

class ngo_one : BaseActivity() {

    private lateinit var mUserDetails:User
    private var mSelectedImageFileUri: Uri? = null
    private var mProfileImageURL : String = ""


    companion object{
        private const val READ_STORAGE_PERMISSION_CODE = 1
        private const val PICK_IMAGE_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_one)
        FirestoreClass().loadUserData(this)

        profileUpdateImagengo.setOnClickListener{
            if(ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    ngo_one.READ_STORAGE_PERMISSION_CODE
                )
            }else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    ngo_one.READ_STORAGE_PERMISSION_CODE
                )
            }
        }

        btnsubmitngo.setOnClickListener {

            if(mSelectedImageFileUri != null){
                uploadUserImage()
            }else{
                showProgressDialog(resources.getString(R.string.please_wait))
                updateUserProfileDatango()
            }
            updateUserProfileDatango()
            val intent = Intent(this@ngo_one,splash4::class.java)
            startActivity(intent)
            finish()

        }

    }




    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== ngo_one.READ_STORAGE_PERMISSION_CODE){
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
        var galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode== PICK_IMAGE_REQUEST_CODE && data!!.data !=null){
            mSelectedImageFileUri = data.data
            try{
                Glide
                    .with(this)
                    .load(mSelectedImageFileUri)
                    .centerCrop()
                    .placeholder(R.drawable.ic_person)
                    .into(profileUpdateImagengo)}catch (e: IOException){
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
                    updateUserProfileDatango()

                }

            }.addOnFailureListener{
                    exception ->
                Toast.makeText(this@ngo_one,exception.message, Toast.LENGTH_LONG).show()
                hideProgressDialog()
            }
        }
    }

    private fun getFileExtension(uri: Uri?): String?{
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri!!))
    }

    private fun updateUserProfileDatango(){
        val userHashMapTwo = HashMap<String,Any>()
//        val name: String = namedonor.text.toString().trim { it <= ' ' }
//        val mobile: String = mobiledonor.text.toString().trim { it <= ' ' }
//        val address: String = addressdonor.text.toString().trim { it <= ' ' }
//        val city: String = citydonor.text.toString().trim{ it<= ' '}

        if(namengo.text.toString()!= mUserDetails.name){
            userHashMapTwo[Constants.NAME] = namengo.text.toString()

        }
        if(mobilengo.text.toString()!= mUserDetails.name.toString()){
            userHashMapTwo[Constants.MOBILE] = mobilengo.text.toString().toLong()

        }
        if(addressngo.text.toString()!= mUserDetails.name){
            userHashMapTwo[Constants.ADDRESS] = addressngo.text.toString()
            userHashMapTwo[Constants.ROLE] = "NGO"

        }
        if(cityngo.text.toString()!= mUserDetails.name){
            userHashMapTwo[Constants.CITY] = cityngo.text.toString()

        }

        FirestoreClass().updateUserProfileDatango(this@ngo_one,userHashMapTwo)

    }

    fun setUserDataInUIngo(user:User){
        mUserDetails = user

        namengo.setText(user.name)
        if(user.mobile != 0L){
            mobilengo.setText(user.mobile.toString())
        }
        if(user.address != ""){
            addressngo.setText(user.address.toString())
        }
        if(user.city != ""){
            cityngo.setText(user.city.toString())
        }
    }

    fun profileUpdateSuccessngo(){
        hideProgressDialog()
        startActivity(Intent(this@ngo_one,splash4::class.java))
        finish()
    }


}
