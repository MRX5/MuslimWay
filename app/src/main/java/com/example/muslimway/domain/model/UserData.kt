package com.example.muslimway.domain.model

data class UserData(
    var email:String,
    var username:String,
    val address:Address
)

class Address(
    var lat:Double,
    var long:Double,
    var city:String
)

