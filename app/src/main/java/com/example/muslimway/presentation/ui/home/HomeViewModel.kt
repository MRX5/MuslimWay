package com.example.muslimway.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslimway.domain.model.PrayerTimes
import com.example.muslimway.domain.use_case.GetTimesFromApiUseCase
import com.example.muslimway.domain.use_case.GetTimesFromCacheUseCase
import com.example.muslimway.utils.DataState
import com.example.muslimway.utils.common.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTimesFromApiUseCase: GetTimesFromApiUseCase,
    private val getTimesFromCacheUseCase: GetTimesFromCacheUseCase,
    private val networkHelper: NetworkHelper
):ViewModel(){
    private val _prayerTimesState=MutableStateFlow<DataState<PrayerTimes>>(DataState.Idle)
            val prayerTimeState:StateFlow<DataState<PrayerTimes>> get() = _prayerTimesState


    fun getPrayerTimesOfDay(date:String){
        viewModelScope.launch {
            getTimesFromCacheUseCase(date).onEach {
                _prayerTimesState.value=it
            }.launchIn(viewModelScope)
        }
    }

    fun getPrayerTimesOfMonth(latitude:Double,longitude:Double,date:String) {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                getTimesFromApiUseCase(latitude, longitude,date).onEach {
                    _prayerTimesState.value = it
                }.launchIn(viewModelScope)
            }
        }
        else{
            _prayerTimesState.value=DataState.Error("Check internet connection")
        }
    }

}