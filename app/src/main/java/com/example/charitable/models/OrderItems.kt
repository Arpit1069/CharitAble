package com.example.charitable.models

data class OrderItems(
    val OrderBooksID: String = "",
    val userName: String = "",
    val quantity: String = "",
    val stdClass: String = "",
    val userMobile: String = "",
    val userAddress: String = "",
    val NGOSelected: String = "",
    val BooksOrderProgress: String = "InProgress",
    var visibility : Boolean = false
){

}
