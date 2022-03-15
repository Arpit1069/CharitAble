package com.example.charitable.models

data class OrderItems(
    val userName : String? = null,
    val quantity : String? = null,
    val stdClass : String? = null,
    val userEmail: String = "",
    val userMobile: String = "",
    val userAddress: String = "",
    val userCity: String = "",
    val userImage: String = "",
    val BooksOrderProgress: String = "",
    val NgosForBooks: String = "",
    val NgosInCity: String = "",
    var visibility : Boolean = false
){

}
