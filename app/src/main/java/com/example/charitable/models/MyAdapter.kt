package com.example.charitable.models

import android.content.Intent
import android.content.pm.PackageManager
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
        val currentUserIDOfBooksOrder = "-MyMvk9HW38-wrfgdU3j"
        database = FirebaseDatabase.getInstance("https://charitable-48fd7-default-rtdb.asia-southeast1.firebasedatabase.app/")
        reference = database.getReference("BooksOrder")
        if (currentUserIDOfBooksOrder.isNotEmpty()){
            reference.child(currentUserIDOfBooksOrder).removeValue()
        }

// TODO : and first save current user id while donating and reload


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
        holder.brief_details_books_name.text = currentitem.OrderBooksID
        holder.brief_details_books_quantity.text = currentitem.quantity
        holder.brief_details_books_class.text = currentitem.stdClass
        holder.brief_details_books_number.text = currentitem.userMobile
        holder.brief_details_books_address.text = currentitem.NGOSelected
//        holder.brief_details_books_city.text = currentitem.userCity

        var status = currentitem.BooksOrderProgress

        holder.buttonmarkasdone.setOnClickListener{

            if(status == "InProgress" ){
                status = "Finished"
            }else{
                status = "was empty"}

        }

        holder.buttonWhatsApp.setOnClickListener{
                v ->
//            val intent = Intent(v.context, MainActivity_Whatsapp::class.java)


            fun onClick(v: View?) {

//                messagestr = message.getText().toString()
//                !messagestr.isEmpty() &&
//                + messagestr



                var phonestr = currentitem.userMobile.toString()
                if (!phonestr.isEmpty()) {

//                    countryCodePicker.registerCarrierNumberEditText(phone)

//                    val countryCodePicker = 91
//                    phonestr = countryCodePicker
//                    if (isWhatappInstalled()) {
                        val i = Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                "https://api.whatsapp.com/send?phone=" + 91 + phonestr +
                                        "&text="
                            )
                        )
                        v?.context?.startActivity(i)
//                        startActivity(i)
                    }
//                    else {
//                        Toast.makeText(
//                            this@MyAdapter,
//                            "Whatsapp is not installed",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                } else {
//                    Toast.makeText(
//                        this@MyAdapter,
//                        "Please fill in the Phone no. and message it can't be empty",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
            }


        }


        val isVisible : Boolean = currentitem.visibility
        holder.contraintLayout.visibility = if(isVisible) View.VISIBLE else View.GONE

        holder.fullViewToExpand.setOnClickListener{

            currentitem.visibility = !currentitem.visibility
//            notifyDataSetChanged()
            notifyItemChanged(position)

        }

    }

    private fun isWhatappInstalled(): Boolean {
        val packageManager: PackageManager
        val whatsappInstalled: Boolean

//        try {
//
//            packageManager.getPackageInfo("com.whatsapp",PackageManager.GET_ACTIVITIES)
//
//            whatsappInstalled = true;
//
//
//        }catch (PackageManager.NameNotFoundException e){
//
//            whatsappInstalled = false;
//
//        }
        whatsappInstalled = true
        return whatsappInstalled
    }


    override fun getItemCount(): Int {
        return userList.size
    }




    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name_booksdonate : TextView = itemView.findViewById(R.id.Username_order_details)
        val quantity_booksdonate : TextView = itemView.findViewById(R.id.quantity_order_details)
        val class_booksdonate : TextView = itemView.findViewById(R.id.standard_order_details)
        val brief_details_books_name : TextView = itemView.findViewById(R.id.briefDetails_books_name)
        val brief_details_books_quantity : TextView = itemView.findViewById(R.id.briefDetails_books_quantity)
        val brief_details_books_class : TextView = itemView.findViewById(R.id.briefDetails_books_class)
        val brief_details_books_number : TextView = itemView.findViewById(R.id.briefDetails_books_number)
        val brief_details_books_address : TextView = itemView.findViewById(R.id.briefDetails_books_address)
        val contraintLayout : ConstraintLayout = itemView.findViewById(R.id.expandedLayout_books)
        val fullViewToExpand : CardView = itemView.findViewById(R.id.click_expand_books)

        val buttonmarkasdone : Button = itemView.findViewById(R.id.btnmarkasdone)
        val buttonWhatsApp : Button = itemView.findViewById(R.id.btnWhatsApp)

    }


}


