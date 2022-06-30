package com.example.muslimway.data.repository

import com.example.muslimway.domain.model.Address
import com.example.muslimway.domain.model.UserData
import com.example.muslimway.domain.repository.AuthRepository
import com.example.muslimway.utils.DataState
import com.example.muslimway.utils.common.NetworkHelper
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
    ) : AuthRepository {

    override suspend fun loginWithEmailAndPassword(email: String, password: String)= callbackFlow{
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    trySend(DataState.Success(true))
                }else{
                    trySend(DataState.Error(it.exception?.message.toString()))
                }
            }
        awaitClose {  }
    }

    override suspend fun loginWithGoogle(
        email: String,
        password: String
    ): Flow<DataState<UserData>> {
        TODO("Not yet implemented")
    }

    override suspend fun registerUser(
        email: String,
        password: String,
    ): Flow<DataState<Boolean>> = callbackFlow {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(DataState.Success(true))
                    } else {
                        trySend(DataState.Error(it.exception?.message.toString()))
                    }
                }
        awaitClose {  }
    }
}
