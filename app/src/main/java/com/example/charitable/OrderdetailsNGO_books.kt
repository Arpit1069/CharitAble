package com.example.charitable

import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.charitable.models.MyAdapter
import com.example.charitable.models.OrderItems
import com.example.charitable.models.SwipeGesture
import com.google.firebase.database.*
import android.content.Context
import java.util.*
import kotlin.collections.ArrayList

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
                    for(userSnapshot in snapshot.children) {

                        val user = userSnapshot.getValue(OrderItems::class.java)
                        userArrayList.add(user!!)


                    }
                    val adapter = MyAdapter(userArrayList)

                    val swipegesture = object : SwipeGesture(this@OrderdetailsNGO_books){

                        override fun onMove(
                            recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder
                        ): Boolean {
                            val from_pos = viewHolder.adapterPosition
                            val to_pos =target.adapterPosition

                            Collections.swap(userArrayList,from_pos,to_pos)
                            adapter.notifyItemMoved(from_pos,to_pos)
                            return false

                        }


                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                            when(direction){

                                ItemTouchHelper.LEFT ->{

                                    adapter.deleteItem(viewHolder.adapterPosition)
                                    adapter.notifyItemRemoved(viewHolder.adapterPosition)

                                }

                                ItemTouchHelper.RIGHT -> {

                                    val archiveItem = userArrayList[viewHolder.adapterPosition]
                                    adapter.deleteItem(viewHolder.adapterPosition)
                                    adapter.addItem(userArrayList.size,archiveItem)

                                }


                            }


                        }
                    }

                    val touchHelper = ItemTouchHelper(swipegesture)
                    touchHelper.attachToRecyclerView(userRecyclerView)

                    userRecyclerView.adapter = MyAdapter(userArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError) {


            }
            
        })
    }
}


