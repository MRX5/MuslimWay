package com.example.muslimway.presentation.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.muslimway.R
import com.example.muslimway.databinding.LoginFragmentBinding
import com.example.muslimway.presentation.base.BaseFragment
import com.example.muslimway.utils.DataState
import com.example.muslimway.utils.extensions.showLongToast
import com.example.muslimway.utils.extensions.showShortToast
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>(R.layout.login_fragment) {

    private val viewModel:LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(FirebaseAuth.getInstance().currentUser?.uid!=null){
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        setupClicks()
        setupObservers()
    }

    override fun setupClicks() {
        binding.pressHereButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginButton.setOnClickListener {
            val email=binding.loginEmail.text.toString()
            val password=binding.loginPassword.text.toString()
            viewModel.loginWithEmailAndPassword(email,password)
        }
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.loginState.collect {
                when(it){
                    is DataState.Loading->{}
                    is DataState.Success->{
                        showShortToast("Welcome")
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    is DataState.Error->{
                        showLongToast(it.msg)
                    }
                }
            }
        }
    }
}

