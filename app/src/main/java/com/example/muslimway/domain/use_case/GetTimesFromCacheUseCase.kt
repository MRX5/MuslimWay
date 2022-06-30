package com.example.muslimway.domain.use_case

import com.example.muslimway.domain.repository.MainRepository
import javax.inject.Inject

class GetTimesFromCacheUseCase @Inject constructor(private val repository: MainRepository){
    suspend operator fun invoke(date:String)=repository.getPrayerTimesFromCache(date)
}