package com.example.charitable.models

data class OrderItems(
    val userName : String? = null,
    val quantity : String? = null,
    val stdClass : String? = null,
    val userEmail: String? = null,
    val userMobile: String? = null,
    val userAddress: String? = null,
    val userCity: String? = null,
    val userImage: String? = null,
    val BooksOrderProgress: String? = null,
    val NgosForBooks: String? = null,
    val NgosInCity: String? = null,
    var visibility : Boolean = false
){

}
