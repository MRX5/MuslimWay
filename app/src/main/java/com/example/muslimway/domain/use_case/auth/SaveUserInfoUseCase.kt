package com.example.muslimway.domain.use_case.auth

import com.example.muslimway.domain.model.Address
import com.example.muslimway.domain.model.UserData
import com.example.muslimway.domain.repository.AuthRepository
import com.example.muslimway.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke(userData: UserData)= repository.saveUserInfo(userData)
}