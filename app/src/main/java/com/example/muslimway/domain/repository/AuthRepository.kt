package com.example.muslimway.domain.repository

import com.example.muslimway.domain.model.Address
import com.example.muslimway.domain.model.UserData
import com.example.muslimway.utils.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun loginWithEmailAndPassword(email:String,password:String): Flow<DataState<Boolean>>

    suspend fun loginWithGoogle(email:String,password:String): Flow<DataState<UserData>>

    suspend fun registerUser(email:String,password: String):Flow<DataState<Boolean>>
}