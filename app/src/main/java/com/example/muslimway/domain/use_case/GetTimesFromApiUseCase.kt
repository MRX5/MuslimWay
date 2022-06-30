package com.example.muslimway.domain.use_case

import com.example.muslimway.domain.repository.MainRepository
import com.example.muslimway.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTimesFromApiUseCase @Inject constructor(
    private val repository: MainRepository
){
    suspend operator fun invoke(latitude:Double, longitude:Double,date:String)=repository.getPrayerTimesFromApi(latitude,longitude,date)
}