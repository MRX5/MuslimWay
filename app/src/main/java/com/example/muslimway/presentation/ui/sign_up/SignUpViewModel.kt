package com.example.muslimway.presentation.ui.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslimway.domain.model.Address
import com.example.muslimway.domain.model.UserData
import com.example.muslimway.domain.use_case.auth.SaveUserInfoUseCase
import com.example.muslimway.domain.use_case.auth.SignUpUseCase
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
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _signupState = MutableStateFlow<DataState<Boolean>>(DataState.Idle)
    val signupState: StateFlow<DataState<Boolean>> get() = _signupState

    fun signup(email: String, username: String, password: String, city: String) {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                signUpUseCase(email, username, password, city).onEach {
                    _signupState.value = it
                }.launchIn(viewModelScope)
            }
        } else {
            _signupState.value=DataState.Error("No internet connection")
        }
    }

    fun saveUserInfo(userData: UserData) {
        viewModelScope.launch {
            saveUserInfoUseCase.invoke(userData)
        }
    }

}