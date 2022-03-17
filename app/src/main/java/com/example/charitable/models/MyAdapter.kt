package com.example.charitable.models

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.charitable.*
import com.example.charitable.databinding.ActivityMainWhatsappBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MyAdapter(private val  userList : ArrayList<OrderItems>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item_books, parent, false)

        return MyViewHolder(itemView)

    }



    fun deleteItem(){
        val currentUserIDOfBooksOrder = userList.toString()
        database = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/")
        reference = database.getReference("Users")
        if (currentUserIDOfBooksOrder.isNotEmpty()){
            reference.child(currentUserIDOfBooksOrder).removeValue()
        }

// TODO : and first save current user id while donating


    }




    fun addItem(position: Int, orders : OrderItems){

//        userList.add(position,orders)
//        notifyDataSetChanged()
//        notifyItemChanged(position)
    }


    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.name_booksdonate.text = currentitem.userName
        holder.quantity_booksdonate.text = currentitem.quantity
        holder.class_booksdonate.text = currentitem.stdClass
//        holder.brief_details_books_name.text = currentitem.userName
        holder.brief_details_books_quantity.text = currentitem.quantity
        holder.brief_details_books_class.text = currentitem.stdClass
//        holder.brief_details_books_email.text = currentitem.userEmail
//        holder.brief_details_books_number.text = currentitem.userMobile
//        holder.brief_details_books_address.text = currentitem.userAddress
//        holder.brief_details_books_city.text = currentitem.userCity

//        var status = currentitem.BooksOrderProgress

        holder.buttonmarkasdone.setOnClickListener{

//            if(status == "Started" ){
//                status = "Finished"
//            }else{
//                status = "was empty"}

        }

        holder.buttonWhatsApp.setOnClickListener{ v ->
            val intent = Intent(v.context, MainActivity_Whatsapp::class.java)
            v.context.startActivity(intent)

        }



        val isVisible : Boolean = currentitem.visibility
        holder.contraintLayout.visibility = if(isVisible) View.VISIBLE else View.GONE

        holder.fullViewToExpand.setOnClickListener{

            currentitem.visibility = !currentitem.visibility
//            notifyDataSetChanged()
            notifyItemChanged(position)

        }

    }


    override fun getItemCount(): Int {
        return userList.size
    }

    fun notifyItemRemoved(fromPos: Int, toPos: Int) {
        notifyItemRemoved(fromPos, toPos)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name_booksdonate : TextView = itemView.findViewById(R.id.Username_order_details)
        val quantity_booksdonate : TextView = itemView.findViewById(R.id.quantity_order_details)
        val class_booksdonate : TextView = itemView.findViewById(R.id.standard_order_details)
        val brief_details_books_name : TextView = itemView.findViewById(R.id.briefDetails_books_name)
        val brief_details_books_quantity : TextView = itemView.findViewById(R.id.briefDetails_books_quantity)
        val brief_details_books_class : TextView = itemView.findViewById(R.id.briefDetails_books_class)
        val brief_details_books_email : TextView = itemView.findViewById(R.id.briefDetails_books_email)
        val brief_details_books_number : TextView = itemView.findViewById(R.id.briefDetails_books_number)
        val brief_details_books_address : TextView = itemView.findViewById(R.id.briefDetails_books_address)
        val brief_details_books_city : TextView = itemView.findViewById(R.id.briefDetails_books_city)
        val contraintLayout : ConstraintLayout = itemView.findViewById(R.id.expandedLayout_books)
        val fullViewToExpand : CardView = itemView.findViewById(R.id.click_expand_books)

        val buttonmarkasdone : Button = itemView.findViewById(R.id.btnmarkasdone)
        val buttonWhatsApp : Button = itemView.findViewById(R.id.btnWhatsApp)

    }


}


