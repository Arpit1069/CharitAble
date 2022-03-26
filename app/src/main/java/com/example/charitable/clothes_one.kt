package com.example.charitable

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.charitable.firebase.FirestoreClass
import com.example.charitable.models.OrderDetails_clothes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_books_one.*
import kotlinx.android.synthetic.main.activity_clothes_one.*

class clothes_one : BaseActivity() {



    private var selectedItemIndex = 0
    private var selectedNGO_clothes = ""
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes_one)
        FirestoreClass().loadUserData(this)
        database = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/")
        reference = database.getReference("ClothesOrder")

        proceedclothes.setOnClickListener {

            sendData_clothes()

        }
    }

    fun showConfirmationDialog_clothes(view: View){

        val NGO_clothes = arrayOf("None","SHREE BHAGAVAN MAHAVIR DIGAMBAR JAIN CHARITABLE TRUST","A S LOGICAL INDIA FOUNDATION",
            "A BOOK OF CHILD'S DREAM FOUNDATION","AABAJI PATIL GRAMIN VIKAS PRATISHTHAN PIMPRI","NARAYANI EMPOWERING INDIA FOUNDATION",
            "EDUCATE EMPOWER ENLIGHTEN FOUNDATION","A I I's A.D. BAWLA FEMALE ORPHANAGE",
            "AADARSH EDUCATIONAL AND WELFARE SOCIETY MALEGAON NASHIK","AADARSH YOUTH FOUNDATION",
            "AADHAR VYASANMUKTI SAMAJIK SEVABHAVI SANSTHA")

        selectedNGO_clothes = NGO_clothes[selectedItemIndex]

        MaterialAlertDialogBuilder(this)
            .setTitle("NGOs For Clothes")
            .setSingleChoiceItems(NGO_clothes, selectedItemIndex){ dialog, which ->
                selectedItemIndex = which
                selectedNGO_clothes = NGO_clothes[which]

            }
            .setPositiveButton("OK") { dialog, which ->

                Toast.makeText(applicationContext,"$selectedNGO_clothes Selected", Toast.LENGTH_LONG).show()
                select_ngo_orderclothes.setText("NGO Selected")

            }
            .setNeutralButton("Cancel") { dialog, which ->
            }
            .show()
    }

    fun sendData_clothes() {

        val nameBooks = name_clothes.text.toString()
        val mobileBooks = mobile_no_clothes.text.toString().trim()
        val addressBooks = address_clothes.text.toString()
        val quantityBooks = quantity_clothes.text.toString().trim()

        val selectedNGO_clothes = selectedNGO_clothes.trim()

        if (quantityBooks.isNotEmpty() && nameBooks.isNotEmpty() && addressBooks.isNotEmpty() &&
            mobileBooks.isNotEmpty() && selectedNGO_clothes.isNotEmpty()){
            if(mobileBooks.length != 10){
                Toast.makeText(this, "INVALID MOBILE NUMBER", Toast.LENGTH_SHORT).show()
            }else{

//            val id = reference.push().key!!

                val id = mobile_no_clothes.text.toString().trim()
                val model = OrderDetails_clothes(id,nameBooks,quantityBooks, mobileBooks, addressBooks, selectedNGO_clothes)

                reference.child(id).setValue(model)

//                quantity_books.setText(id)
//                standard_books.setText(selectedNGO_clothes)
                Toast.makeText(applicationContext,"Successfully send to NGO", Toast.LENGTH_LONG).show()

                val intent = Intent(this@clothes_one, splash2::class.java)
                startActivity(intent)
                finish()}

        }else{
            Toast.makeText(applicationContext,"All fields are Required", Toast.LENGTH_LONG).show()
        }

    }
}


