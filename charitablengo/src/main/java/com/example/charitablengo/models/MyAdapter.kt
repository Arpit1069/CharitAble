package com.example.charitablengo.models

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.charitable.*
import com.example.charitablengo.OrderdetailsNGO_books
import com.example.charitablengo.R
import com.example.charitablengo.models.OrderItems
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MyAdapter(private val  userList : ArrayList<OrderItems>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

private  var color = "#FF23BF00"
    private lateinit var database: FirebaseDatabase
    private lateinit var dbref : DatabaseReference
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item_books, parent, false)

        return MyViewHolder(itemView)

    }



//    fun addItem(position: Int, orders : OrderItems){
//
////        userList.add(position,orders)
////        notifyDataSetChanged()
////        notifyItemChanged(position)
//
//    }


    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.name_booksdonate.text = currentitem.userName
        holder.quantity_booksdonate.text = currentitem.userAddress
        holder.brief_details_books_order_id.text = currentitem.OrderBooksID
        holder.brief_details_books_quantity.text = currentitem.quantity
        holder.brief_details_books_class.text = currentitem.stdClass
        holder.brief_details_books_number.text = currentitem.userMobile
        holder.brief_details_books_address.text = currentitem.userAddress
        holder.brief_details_OrderStatus_Books.text = currentitem.BooksOrderProgress
        holder.brief_details_OrderStatus_Books.setBackgroundColor(Color.parseColor(color))
        holder.brief_details_books_selectedNGO.text = currentitem.NGOSelected


        var status = currentitem.BooksOrderProgress

        holder.buttonmarkasdone.setOnClickListener{

            if(status == "InProgress" ){
                status = "Finished"
                color = "grey"
            }

            currentitem.BooksOrderProgress = status
            holder.brief_details_OrderStatus_Books.setBackgroundColor(Color.parseColor(color))
            holder.brief_details_OrderStatus_Books.text = currentitem.BooksOrderProgress

//            deleteItem(position)
            return@setOnClickListener
        }

        holder.buttonWhatsApp.setOnClickListener{
                v ->

                val phonestr = currentitem.userMobile
                if (!phonestr.isEmpty()) {


                        val i = Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                "https://api.whatsapp.com/send?phone=" + "91" + phonestr +
                                        "&text="
                            )
                        )
                        v?.context?.startActivity(i)

                    }
        }


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

    fun deleteItem(position: Int) {
        val currentitem = userList[position]
        dbref = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("BooksOrder")
        dbref.child(currentitem.userMobile.toString()).removeValue()
        notifyItemChanged(position)

//        val intent = Intent(this@MyAdapter.requireContext(), OrderdetailsNGO_books::class.java)
//        startActivity(intent)

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name_booksdonate : TextView = itemView.findViewById(R.id.Username_order_details)
        val quantity_booksdonate : TextView = itemView.findViewById(R.id.address_order_details)
        val brief_details_books_order_id : TextView = itemView.findViewById(R.id.briefDetails_books_order_id)
        val brief_details_books_quantity : TextView = itemView.findViewById(R.id.briefDetails_books_quantity)
        val brief_details_books_class : TextView = itemView.findViewById(R.id.briefDetails_books_class)
        val brief_details_books_number : TextView = itemView.findViewById(R.id.briefDetails_books_number)
        val brief_details_books_address : TextView = itemView.findViewById(R.id.briefDetails_books_address)

        val brief_details_OrderStatus_Books : TextView = itemView.findViewById(R.id.OrderStatus_Books)
        val brief_details_books_selectedNGO : TextView = itemView.findViewById(R.id.briefDetails_books_selectedNGO)


        val contraintLayout : ConstraintLayout = itemView.findViewById(R.id.expandedLayout_books)
        val fullViewToExpand : CardView = itemView.findViewById(R.id.click_expand_books)

        val buttonmarkasdone : Button = itemView.findViewById(R.id.btnmarkasdone)
        val buttonWhatsApp : Button = itemView.findViewById(R.id.btnWhatsApp)

    }


}


