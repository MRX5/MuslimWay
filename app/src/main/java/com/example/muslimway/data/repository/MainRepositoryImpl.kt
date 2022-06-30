package com.example.muslimway.data.repository

import com.example.muslimway.data.local.MuslimDao
import com.example.muslimway.data.local.entity.PrayerTimesEntity
import com.example.muslimway.data.remote.ApiService
import com.example.muslimway.domain.repository.MainRepository
import com.example.muslimway.utils.DataState
import com.example.muslimway.utils.extensions.convertToDatabaseEntity
import com.example.muslimway.utils.extensions.toDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val muslimDao: MuslimDao,
    private val ioDispatcher: CoroutineDispatcher
):MainRepository{

    override suspend fun getPrayerTimesFromApi(latitude:Double,longitude:Double,date:String)= flow {
       val response=apiService.getPrayerTimes(latitude,longitude)
        if(response.isSuccessful){
            if(response.code()==200){
                response.body()?.let {
                    muslimDao.insertPrayerTimes(it.data.convertToDatabaseEntity())
                    val prayerTimesOfDay=muslimDao.getPrayerTime(date)
                    emit(DataState.Success(prayerTimesOfDay!!.toDomainModel()))
                }?: emit(DataState.Error("There is no data"))
            }
            else{
                emit(DataState.Error("There is no data"))
            }
        }else{
            emit(DataState.Error(response.message()))
        }
    }.flowOn(ioDispatcher)


    override suspend fun getPrayerTimesFromCache(date: String)=flow {
        val times=muslimDao.getPrayerTime(date)
        if(times==null){
            emit(DataState.Error("No data"))
        }else{
            emit(DataState.Success(times.toDomainModel()))
        }
    }

}