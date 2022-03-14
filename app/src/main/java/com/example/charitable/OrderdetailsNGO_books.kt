package com.example.charitable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.charitable.models.MyAdapter
import com.example.charitable.models.OrderItems
import com.google.firebase.database.*
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase

class OrderdetailsNGO_books : BaseActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<OrderItems>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orderdetails_ngo)

        userRecyclerView = findViewById(R.id.orderdetails_NGO)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<OrderItems>()
        getUserData()

    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for(userSnapshot in snapshot.children){

                        val user = userSnapshot.getValue(OrderItems::class.java)
                        userArrayList.add(user!!)

                    }

                    userRecyclerView.adapter = MyAdapter(userArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError) {


            }
            
        })
    }
}

