package com.example.charitable.models

data class OrderItems(
    val userName : String? = null,
    val quantity : String = "",
    val stdClass : String = "",
//    val userEmail: String = "",
//    val userMobile: String = "",
//    val userAddress: String = "",
//    val userCity: String = "",
//    val userImage: String = "",
    val BooksOrderProgress: String = "InProgress",
//    val NgosForBooks: String = "",
//    val NgosInCity: String = "",
    var visibility : Boolean = false
){

}
