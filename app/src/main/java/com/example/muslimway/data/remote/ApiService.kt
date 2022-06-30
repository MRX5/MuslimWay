package com.example.muslimway.data.remote

import com.example.muslimway.data.remote.dto.PrayerTimeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/calendar")
    suspend fun getPrayerTimes(@Query("latitude") latitude:Double,@Query("longitude") longitude:Double):Response<PrayerTimeDto>

}