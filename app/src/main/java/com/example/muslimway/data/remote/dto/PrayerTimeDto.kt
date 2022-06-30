package com.example.muslimway.data.remote.dto

data class PrayerTimeDto(
    val data:List<DayOfMonth>
)

data class DayOfMonth(
    val timings: Timings,
    val date:DayInfo
)

data class Timings(
    var Fajr:String,
    var Sunrise:String,
    var Dhuhr:String,
    var Asr:String,
    var Maghrib:String,
    var Isha:String,
)

data class DayInfo(
    val readable:String
)