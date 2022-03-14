package com.example.charitable.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.charitable.R

class MyAdapter(private val  userList : ArrayList<OrderItems>) : RecyclerView.Adapter<MyAdapter.MyVeiwHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVeiwHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item_books, parent, false)

        return MyVeiwHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyVeiwHolder, position: Int) {

        val currentitem = userList[position]

        holder.name_booksdonate.text = currentitem.userName
        holder.class_booksdonate.text = currentitem.stdClass
        holder.quantity_booksdonate.text = currentitem.quantity


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyVeiwHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name_booksdonate : TextView = itemView.findViewById(R.id.Username_order_details)
        val class_booksdonate : TextView = itemView.findViewById(R.id.quantity_order_details)
        val quantity_booksdonate : TextView = itemView.findViewById(R.id.standard_order_details)

    }


}