package com.example.muslimway.core.di

import android.content.Context
import androidx.room.Room
import com.example.muslimway.data.local.MuslimDao
import com.example.muslimway.data.local.MuslimDatabase
import com.example.muslimway.data.remote.ApiService
import com.example.muslimway.utils.Constants
import com.example.muslimway.utils.Constants.BASE_URL
import com.example.muslimway.utils.common.NetworkHelper
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import okhttp3.OkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth()=Firebase.auth

    @Singleton
    @Provides
    fun provideFirestore()=FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context):NetworkHelper= NetworkHelper(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging=HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient().newBuilder().addInterceptor(logging).build()
    }

    @Singleton
    @Provides
    fun providePrayerTimesApiService(client: OkHttpClient):ApiService=Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideMuslimDatabase(@ApplicationContext context: Context):MuslimDatabase=
        Room.databaseBuilder(context,MuslimDatabase::class.java,Constants.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMuslimDao(database: MuslimDatabase):MuslimDao=database.muslimDao()
}