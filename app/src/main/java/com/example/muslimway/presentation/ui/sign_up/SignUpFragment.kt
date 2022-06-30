package com.example.muslimway.presentation.ui.sign_up

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.muslimway.R
import com.example.muslimway.databinding.SignUpFragmentBinding
import com.example.muslimway.domain.model.Address
import com.example.muslimway.domain.model.UserData
import com.example.muslimway.presentation.base.BaseFragment
import com.example.muslimway.utils.DataState
import com.example.muslimway.utils.common.NetworkHelper
import com.example.muslimway.utils.extensions.getCityName
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.*
import javax.inject.Inject

const val TAG = "mostafa"

@AndroidEntryPoint
class SignUpFragment : BaseFragment<SignUpFragmentBinding>(R.layout.sign_up_fragment) {

    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var address: Address

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        setupClicks()
        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.signupState.collect {
                when (it) {
                    is DataState.Loading -> {
                        Log.d(TAG, "loading: ")
                    }
                    is DataState.Success -> {
                        Log.d(TAG, "success")
                        val email = binding.signUpEmail.text.toString()
                        val username = binding.signUpUsername.text.toString()
                        val userData = UserData(email, username, address)
                        viewModel.saveUserInfo(userData)
                    }
                    is DataState.Error -> {
                        Toast.makeText(context, it.msg, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun setupClicks() {
        binding.signUpLocation.setOnClickListener {
            getCurrentLocation()
        }

        binding.registerSignupButton.setOnClickListener {
            with(binding) {
                viewModel.signup(
                    signUpEmail.text.toString(),
                    signUpUsername.text.toString(),
                    signUpPassword.text.toString(),
                    signUpLocation.text.toString()
                )
            }
        }

        binding.backToLoginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }


    private fun getCurrentLocation() {
        Dexter.withContext(context)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                @SuppressLint("MissingPermission")
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show()
                            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                                if (it != null) {
                                    val geocoder = Geocoder(requireContext(), Locale.getDefault())
                                    val list =
                                        geocoder.getFromLocation(it.latitude, it.longitude, 2)
                                    val cityName = list[1].getAddressLine(0).getCityName()
                                    binding.signUpLocation.setText(cityName)
                                    address = Address(
                                        lat = it.latitude,
                                        long = it.longitude,
                                        city = cityName
                                    )
                                }
                            }
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                }

            }).check()

    }
}