package com.example.muslimway.domain.model

data class PrayerTimes(
    val date:String,
    var fajr:String,
    var sunrise:String,
    var dhuhr:String,
    var asr:String,
    var maghrib:String,
    var isha:String,
)
