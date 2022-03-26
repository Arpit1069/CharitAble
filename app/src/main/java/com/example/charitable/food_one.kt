package com.example.charitable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.charitable.models.OrderDetails_clothes
import com.example.charitable.models.OrderDetails_food
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_clothes_one.*
import kotlinx.android.synthetic.main.activity_food_one.*

class food_one : BaseActivity() {


    private var selectedItemIndex = 0
    private var selectedNGO_food = ""
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_one)
        database = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/")
        reference = database.getReference("FoodOrder")

        proceed_food.setOnClickListener {
            sendData_food()
        }

    }

    fun showConfirmationDialog_food(view: View){

        val NGO_food = arrayOf("None","SHREE BHAGAVAN MAHAVIR DIGAMBAR JAIN CHARITABLE TRUST","A S LOGICAL INDIA FOUNDATION",
            "A BOOK OF CHILD'S DREAM FOUNDATION","AABAJI PATIL GRAMIN VIKAS PRATISHTHAN PIMPRI","NARAYANI EMPOWERING INDIA FOUNDATION",
            "EDUCATE EMPOWER ENLIGHTEN FOUNDATION","A I I's A.D. BAWLA FEMALE ORPHANAGE",
            "AADARSH EDUCATIONAL AND WELFARE SOCIETY MALEGAON NASHIK","AADARSH YOUTH FOUNDATION",
            "AADHAR VYASANMUKTI SAMAJIK SEVABHAVI SANSTHA")

        selectedNGO_food = NGO_food[selectedItemIndex]

        MaterialAlertDialogBuilder(this)
            .setTitle("NGOs For Food")
            .setSingleChoiceItems(NGO_food, selectedItemIndex){ dialog, which ->
                selectedItemIndex = which
                selectedNGO_food = NGO_food[which]

            }
            .setPositiveButton("OK") { dialog, which ->

                Toast.makeText(applicationContext,"$selectedNGO_food Selected", Toast.LENGTH_LONG).show()
                select_ngo_food.setText("NGO Selected")

            }
            .setNeutralButton("Cancel") { dialog, which ->
            }
            .show()
    }

    fun sendData_food() {

        val nameBooks = name_food.text.toString()
        val mobileBooks = mobile_no_food.text.toString().trim()
        val addressBooks = address_food.text.toString()
        val quantityBooks = quantity_food.text.toString().trim()

        val selectedNGO_food = selectedNGO_food.trim()

        if (quantityBooks.isNotEmpty() && nameBooks.isNotEmpty() && addressBooks.isNotEmpty() &&
            mobileBooks.isNotEmpty() && selectedNGO_food.isNotEmpty()){
            if(mobileBooks.length != 10){
                Toast.makeText(this, "INVALID MOBILE NUMBER", Toast.LENGTH_SHORT).show()
            }else{

//            val id = reference.push().key!!

                val id = mobile_no_food.text.toString().trim()
                val model = OrderDetails_food(id,nameBooks,quantityBooks, mobileBooks, addressBooks, selectedNGO_food)

                reference.child(id).setValue(model)

//                quantity_books.setText(id)
//                standard_books.setText(selectedNGO_clothes)
                Toast.makeText(applicationContext,"Successfully send to NGO", Toast.LENGTH_LONG).show()

                val intent = Intent(this@food_one, splash2::class.java)
                startActivity(intent)
                finish()}

        }else{
            Toast.makeText(applicationContext,"All fields are Required", Toast.LENGTH_LONG).show()
        }

    }
}