package com.example.charitable

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_home2.*
import java.util.jar.Manifest

class HomeFragment_res : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home2, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment_res().apply {
                arguments = Bundle().apply {}
            }
    }
}


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        setContentView(R.layout.fragment_home2)
//     return inflater.inflate(R.layout.fragment_home2, container, false)
//    }
//}
//    private fun setContentView(fragmentHome2: Int) {
//        pick_btn.setOnClickListener {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
//                    PackageManager.PERMISSION_DENIED){
//                    val permission = arrayOf(READ_EXTERNAL_STORAGE).apply {
//
//                    }
//                }
//                else{
//                    pickImageFromGallery();
//                }
//            }
//            else{
//                pickImageFromGallery();
//            }
//        }
//
//
//    }
//
//    private fun checkSelfPermission(readExternalStorage: Context): Int {
//        return 0;
//
//    }
//
//    private fun pickImageFromGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, IMAGE_PICK_CODE)
//    }
//
//
//    companion object {
//        private val IMAGE_PICK_CODE = 1000;
//        private val PERMISSION_CODE = 1001;
//        @JvmStatic
//        fun newInstance() =
//            HomeFragment_res().apply {
//                arguments = Bundle().apply {}
//
//            }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults:  )
//        when(requestCode){
//            PERMISSION_CODE -> {
//                if (grantResults.size >0 && grantResults[0] ==
//                    PackageManager.PERMISSION_GRANTED){
//                    pickImageFromGallery()
//                }
//                else{
//                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//     fun onRequestPermissionResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
//            image_view.setImageURI(data?.data)
//        }
//    }
//
//}
//
//private infix fun Unit.out(string: String): Any {
//    return 0;
//
//}
