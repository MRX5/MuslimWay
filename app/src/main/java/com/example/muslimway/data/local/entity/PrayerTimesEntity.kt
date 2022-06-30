package com.example.muslimway.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PrayerTimesEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val date:String,
    var fajr:String,
    var sunrise:String,
    var dhuhr:String,
    var asr:String,
    var maghrib:String,
    var isha:String,
)
