package com.example.muslimway.presentation.ui.onboarding

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.muslimway.R
import com.example.muslimway.databinding.OnBoardingFragmentBinding
import com.example.muslimway.presentation.base.BaseFragment

class OnBoardingFragment : BaseFragment<OnBoardingFragmentBinding>(R.layout.on_boarding_fragment) {

    private val viewModel:OnBoardingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}