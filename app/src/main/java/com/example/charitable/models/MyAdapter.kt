package com.example.charitable.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.charitable.R

class MyAdapter(private val  userList : ArrayList<OrderItems>) : RecyclerView.Adapter<MyAdapter.MyVeiwHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVeiwHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item_books, parent, false)

        return MyVeiwHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyAdapter.MyVeiwHolder, position: Int) {

        val currentitem = userList[position]

        holder.name_booksdonate.text = currentitem.userName
        holder.class_booksdonate.text = currentitem.stdClass
        holder.quantity_booksdonate.text = currentitem.quantity
        holder.brief_details_books.text = currentitem.userEmail

        val isVisible : Boolean = currentitem.visibility
        holder.contraintLayout.visibility = if(isVisible) View.VISIBLE else View.GONE

        holder.fullViewToExpand.setOnClickListener{

            currentitem.visibility = !currentitem.visibility
            notifyItemChanged(position)

        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyVeiwHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name_booksdonate : TextView = itemView.findViewById(R.id.Username_order_details)
        val class_booksdonate : TextView = itemView.findViewById(R.id.quantity_order_details)
        val quantity_booksdonate : TextView = itemView.findViewById(R.id.standard_order_details)
        val brief_details_books : TextView = itemView.findViewById(R.id.briefDetails_books)
        val contraintLayout : ConstraintLayout = itemView.findViewById(R.id.expandedLayout_books)
        val fullViewToExpand : CardView = itemView.findViewById(R.id.click_expand_books)

    }


}


