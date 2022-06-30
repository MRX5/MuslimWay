package com.example.muslimway.domain.use_case.auth

import com.example.muslimway.domain.repository.AuthRepository
import com.example.muslimway.utils.DataState
import com.example.muslimway.utils.extensions.isValidEmail
import com.example.muslimway.utils.extensions.isValidPassword
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginWithEmailUseCase @Inject constructor(private val repository: AuthRepository){
    suspend operator fun invoke(email:String,password:String)= flow{
        if(email.isValidEmail() && password.isValidPassword()) {
            emitAll(repository.loginWithEmailAndPassword(email, password))
        }else{
            emit(DataState.Error("Invalid email or password"))
        }
    }
}