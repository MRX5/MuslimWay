package com.example.muslimway.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslimway.domain.use_case.auth.LoginWithEmailUseCase
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
class LoginViewModel @Inject constructor(
    private val loginWithEmailUseCase: LoginWithEmailUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _loginState = MutableStateFlow<DataState<Boolean>>(DataState.Idle)
    val loginState: StateFlow<DataState<Boolean>> get() = _loginState

    fun loginWithEmailAndPassword(email: String, password: String) {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                loginWithEmailUseCase(email, password).onEach {
                    _loginState.value = it
                }.launchIn(viewModelScope)
            }
        }else{
            _loginState.value=DataState.Error("لا يوجد اتصال بالإنترنت")
        }
    }
}