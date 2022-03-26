package com.example.charitablengo.models

data class OrderItems_clothes(
    val OrderClothesID: String = "",
    val userName: String = "",
    val quantity: String = "",
    val userMobile: String = "",
    val userAddress: String = "",
    val NGOSelected: String = "",
    var ClothesOrderProgress: String = "InProgress",
    var visibility : Boolean = false
){

}
