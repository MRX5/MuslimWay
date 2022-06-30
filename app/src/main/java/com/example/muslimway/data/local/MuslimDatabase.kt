package com.example.muslimway.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.muslimway.data.local.entity.PrayerTimesEntity

@Database(entities = [PrayerTimesEntity::class], version = 1, exportSchema = false)
abstract class MuslimDatabase:RoomDatabase() {
    abstract fun muslimDao():MuslimDao
}