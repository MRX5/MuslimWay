package com.example.muslimway.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.muslimway.R
import com.example.muslimway.databinding.FragmentHomeBinding
import com.example.muslimway.domain.model.PrayerTimes
import com.example.muslimway.presentation.base.BaseFragment
import com.example.muslimway.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel:HomeViewModel by viewModels()
    private lateinit var currentDate:String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentDate()
        setupRecyclerView()
        getPrayerTimes()
        setupObservers()
    }

    private fun getCurrentDate() {
        val calendar= Calendar.getInstance().time
        val df = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        currentDate = df.format(calendar)
    }

    private fun setupRecyclerView() {

    }


    private fun getPrayerTimes() {
        viewModel.getPrayerTimesOfDay(currentDate)
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.prayerTimeState.collect {
                when(it){
                    is DataState.Loading->{

                    }
                    is DataState.Success->{
                        populateUi(it.data)
                    }
                    is DataState.Error->{
                        if(it.msg=="No data"){
                            viewModel.getPrayerTimesOfMonth(30.275025,31.3115648,currentDate)
                        }
                    }
                }
            }
        }
    }

    private fun populateUi(data: PrayerTimes) {
        binding.apply {
            fajrPrayerTime.text=data.fajr
            sunrisePrayerTime.text=data.sunrise
            dhuhrPrayerTime.text=data.dhuhr
            asrPrayerTime.text=data.asr
            dhuhrCardView.strokeWidth=2
            dhuhrCardView.strokeColor=resources.getColor(R.color.light_green,null)
            maghribPrayerTime.text=data.maghrib
            IshaPrayerTime.text=data.isha
        }
    }


}