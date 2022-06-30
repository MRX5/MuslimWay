package com.example.muslimway.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.muslimway.data.local.entity.PrayerTimesEntity

@Dao
interface MuslimDao {

    @Insert
    suspend fun insertPrayerTimes(prayerTimes:List<PrayerTimesEntity>)

    @Query("SELECT * FROM PrayerTimesEntity WHERE date= :date")
    suspend fun getPrayerTime(date:String):PrayerTimesEntity?

}