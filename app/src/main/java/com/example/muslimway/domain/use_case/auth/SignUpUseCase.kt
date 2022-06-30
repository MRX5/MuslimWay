package com.example.muslimway.domain.use_case.auth

import com.example.muslimway.domain.model.Address
import com.example.muslimway.domain.repository.AuthRepository
import com.example.muslimway.utils.DataState
import com.example.muslimway.utils.extensions.isValidEmail
import com.example.muslimway.utils.extensions.isValidLocation
import com.example.muslimway.utils.extensions.isValidPassword
import com.example.muslimway.utils.extensions.isValidUsername
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository:AuthRepository) {
    suspend operator fun invoke(email:String, username:String, password:String, city:String) =flow{
        if(email.isValidEmail() && username.isValidUsername() && password.isValidPassword() && city.isValidLocation()){
            emitAll(repository.registerUser(email,password))
        }else{
            emit(DataState.Error("Invalid user data"))
        }
    }
}