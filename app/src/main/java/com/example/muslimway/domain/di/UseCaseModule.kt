package com.example.muslimway.domain.di

import com.example.muslimway.domain.repository.AuthRepository
import com.example.muslimway.domain.repository.MainRepository
import com.example.muslimway.domain.repository.UserRepository
import com.example.muslimway.domain.use_case.GetTimesFromCacheUseCase
import com.example.muslimway.domain.use_case.GetTimesFromApiUseCase
import com.example.muslimway.domain.use_case.auth.LoginWithEmailUseCase
import com.example.muslimway.domain.use_case.auth.SaveUserInfoUseCase
import com.example.muslimway.domain.use_case.auth.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideSignUpUseCase(repository:AuthRepository):SignUpUseCase=SignUpUseCase(repository)

    @Singleton
    @Provides
    fun provideSaveUserInfoUseCase(repository: UserRepository):SaveUserInfoUseCase=SaveUserInfoUseCase(repository)

    @Singleton
    @Provides
    fun provideLoginWithEmailUseCase(repository: AuthRepository):LoginWithEmailUseCase=LoginWithEmailUseCase(repository)

    @Singleton
    @Provides
    fun provideGetTimesFromApiUseCase(repository: MainRepository):GetTimesFromApiUseCase=GetTimesFromApiUseCase(repository)

    @Singleton
    @Provides
    fun provideGetTimesFromCacheUseCase(repository: MainRepository):GetTimesFromCacheUseCase=GetTimesFromCacheUseCase(repository)
}