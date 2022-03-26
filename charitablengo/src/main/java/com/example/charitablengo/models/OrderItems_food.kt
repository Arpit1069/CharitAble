package com.example.charitablengo.models

data class OrderItems_food(
    val OrderFoodID: String = "",
    val userName_Food: String = "",
    val quantity_Food: String = "",
    val userMobile_Food: String = "",
    val userAddress_Food: String = "",
    val NGOSelected_Food: String = "",
    var ClothesOrderProgress: String = "InProgress",
    var visibility : Boolean = false
){

}
