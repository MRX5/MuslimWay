package com.example.muslimway.utils.extensions

import android.util.Log

fun String.isValidEmail(): Boolean {
    return this.isNotEmpty()
}

fun String.isValidUsername(): Boolean {
    return this.length >= 8
}

fun String.isValidPassword(): Boolean {
    return this.length >= 8
}

fun String.isValidLocation(): Boolean {
    return this.isNotEmpty()
}

fun String.extractPrayerTime(): String {
    var hour = substring(0, 1).toInt()
    val min = substring(3, 4)
    
    if (hour >= 12) {
        if (hour > 12) hour -= 12
        if(hour<10)return "0$hour:$min"
        return "$hour:$min"
    }
    if(hour<10)return "0$hour:$min"
    return "$hour:$min"
}

fun String.getCityName(): String {
    return this.split("،")[0]
}


