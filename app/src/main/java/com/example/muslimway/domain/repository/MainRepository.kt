package com.example.muslimway.domain.repository

import com.example.muslimway.data.remote.dto.PrayerTimeDto
import com.example.muslimway.domain.model.PrayerTimes
import com.example.muslimway.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getPrayerTimesFromApi(latitude:Double,longitude:Double,date:String): Flow<DataState<PrayerTimes>>

    suspend fun getPrayerTimesFromCache(date:String):Flow<DataState<PrayerTimes>>
}