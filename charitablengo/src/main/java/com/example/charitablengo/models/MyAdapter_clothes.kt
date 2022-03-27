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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.charitable.*
import com.example.charitablengo.R
import com.example.charitablengo.models.OrderItems
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MyAdapter_clothes(private val  userList : ArrayList<OrderItems_clothes>) : RecyclerView.Adapter<MyAdapter_clothes.MyViewHolder_clothes>() {

private  var color = "#FF23BF00"
    private lateinit var database: FirebaseDatabase
    private lateinit var dbref : DatabaseReference
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder_clothes {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item_clothes, parent, false)

        return MyViewHolder_clothes(itemView)

    }



//    fun addItem(position: Int, orders : OrderItems){
//
////        userList.add(position,orders)
////        notifyDataSetChanged()
////        notifyItemChanged(position)
//
//    }


    override fun onBindViewHolder(holder: MyAdapter_clothes.MyViewHolder_clothes, position_clothes: Int) {

        val currentitem = userList[position_clothes]

        holder.name_booksdonate.text = currentitem.userName_Clothes
        holder.quantity_booksdonate.text = currentitem.quantity_Clothes
        holder.brief_details_books_quantity.text = currentitem.quantity_Clothes
        holder.brief_details_books_number.text = currentitem.userMobile_Clothes
        holder.brief_details_books_address.text = currentitem.userAddress_Clothes
        holder.brief_details_OrderStatus_Books.text = currentitem.ClothesOrderProgress
        holder.brief_details_OrderStatus_Books.setBackgroundColor(Color.parseColor(color))



        var status = currentitem.ClothesOrderProgress

        holder.buttonmarkasdone.setOnClickListener{

            if(status == "InProgress" ){
                status = "Finished"
                color = "grey"
            }

            currentitem.ClothesOrderProgress = status
            holder.brief_details_OrderStatus_Books.setBackgroundColor(Color.parseColor(color))
            holder.brief_details_OrderStatus_Books.text = currentitem.ClothesOrderProgress

//            deleteItem(position)
            return@setOnClickListener
        }

        holder.buttonWhatsApp.setOnClickListener{
                v ->

                val phonestr = currentitem.userMobile_Clothes
                if (!phonestr.isEmpty()) {


                        val i = Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                "https://api.whatsapp.com/send?phone=" + "91" + phonestr +
                                        "&text="
                            )
                        )
                        v?.context?.startActivity(i)

                    }

//                else {
//                    Toast.makeText(
//                        this@MyAdapter,
//                        "Please fill in the Phone no. and message it can't be empty",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }




        }


        val isVisible : Boolean = currentitem.visibility
        holder.contraintLayout.visibility = if(isVisible) View.VISIBLE else View.GONE

        holder.fullViewToExpand.setOnClickListener{

            currentitem.visibility = !currentitem.visibility
            notifyItemChanged(position_clothes)

        }

    }



    override fun getItemCount(): Int {
        return userList.size
    }

    fun deleteItem(position: Int) {
        val currentitem = userList[position]
        dbref = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("ClothesOrder")
        dbref.child(currentitem.userMobile_Clothes.toString()).removeValue()
        notifyItemChanged(position)

//        TODO("Not yet implemented")
    }


    class MyViewHolder_clothes(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name_booksdonate : TextView = itemView.findViewById(R.id.Username_order_details_clothes)
        val quantity_booksdonate : TextView = itemView.findViewById(R.id.address_order_details_clothes)

        val brief_details_books_quantity : TextView = itemView.findViewById(R.id.briefDetails_clothes_quantity)
        val brief_details_books_number : TextView = itemView.findViewById(R.id.briefDetails_clothes_number)
        val brief_details_books_address : TextView = itemView.findViewById(R.id.briefDetails_clothes_address)

        val brief_details_OrderStatus_Books : TextView = itemView.findViewById(R.id.OrderStatus_clothes)



        val contraintLayout : ConstraintLayout = itemView.findViewById(R.id.expandedLayout_clothes)
        val fullViewToExpand : CardView = itemView.findViewById(R.id.click_expand_clothes)

        val buttonmarkasdone : Button = itemView.findViewById(R.id.btnmarkasdone_clothes)
        val buttonWhatsApp : Button = itemView.findViewById(R.id.btnWhatsApp_clothes)

    }


}


