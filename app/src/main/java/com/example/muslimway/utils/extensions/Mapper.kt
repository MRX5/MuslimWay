package com.example.muslimway.utils.extensions

import android.util.Log
import com.example.muslimway.data.local.entity.PrayerTimesEntity
import com.example.muslimway.data.remote.dto.DayOfMonth
import com.example.muslimway.domain.model.PrayerTimes

fun List<DayOfMonth>.convertToDatabaseEntity(): MutableList<PrayerTimesEntity> {
    val list = mutableListOf<PrayerTimesEntity>()
    this.forEach {
        val prayerTimes=it.timings
        val date=it.date.readable
        list.add(
            PrayerTimesEntity(
                date = date,
                fajr = prayerTimes.Fajr.extractPrayerTime(),
                sunrise = prayerTimes.Sunrise.extractPrayerTime(),
                dhuhr = prayerTimes.Dhuhr.extractPrayerTime(),
                asr = prayerTimes.Asr.extractPrayerTime(),
                maghrib = prayerTimes.Maghrib.extractPrayerTime(),
                isha = prayerTimes.Isha.extractPrayerTime()
            )
        )
    }
    return list
}

fun PrayerTimesEntity.toDomainModel()=PrayerTimes(
    date = this.date,
    fajr = this.fajr,
    sunrise = this.sunrise,
    dhuhr = this.dhuhr,
    asr = this.asr,
    maghrib = this.maghrib,
    isha = this.isha
)
