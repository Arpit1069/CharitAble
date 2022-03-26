package com.example.charitablengo.models

data class OrderItems_clothes(
    val OrderClothesID: String = "",
    val userName_Clothes: String = "",
    val quantity_Clothes: String = "",
    val userMobile_Clothes: String = "",
    val userAddress_Clothes: String = "",
    val NGOSelected_Clothes: String = "",
    var ClothesOrderProgress: String = "InProgress",
    var visibility : Boolean = false
){

}
