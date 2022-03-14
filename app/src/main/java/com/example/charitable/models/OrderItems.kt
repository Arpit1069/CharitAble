package com.example.charitable.models

data class OrderItems(
    val quantity : String? = null,
    val stdClass : String? = null,
    val userName : String? = null,
    val userCity: String = "",
    val userEmail: String = "",
    val userImage: String = "",
    val userMobile: String = "",
    val userAddress: String = "",
    val BooksOrderProgress: String = "",
    val NgosForBooks: String = "",
    val NgosInCity: String = "",
    var visibility : Boolean = false
){

}
