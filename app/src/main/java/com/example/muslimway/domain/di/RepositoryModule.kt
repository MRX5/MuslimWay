package com.example.muslimway.domain.di

import com.example.muslimway.data.local.MuslimDao
import com.example.muslimway.data.remote.ApiService
import com.example.muslimway.data.repository.AuthRepositoryImpl
import com.example.muslimway.data.repository.MainRepositoryImpl
import com.example.muslimway.data.repository.UserRepositoryImpl
import com.example.muslimway.domain.repository.AuthRepository
import com.example.muslimway.domain.repository.MainRepository
import com.example.muslimway.domain.repository.UserRepository
import com.example.muslimway.utils.common.NetworkHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth):AuthRepository=AuthRepositoryImpl(firebaseAuth)

    @Singleton
    @Provides
    fun provideUserRepository(firebaseAuth: FirebaseAuth,firestore:FirebaseFirestore):UserRepository=UserRepositoryImpl(firebaseAuth,firestore)

    @Singleton
    @Provides
    fun provideMainRepository(apiService:ApiService,muslimDao: MuslimDao,dispatcher: CoroutineDispatcher):MainRepository=
        MainRepositoryImpl(apiService,muslimDao,dispatcher)


}