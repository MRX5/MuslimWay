package com.example.muslimway.domain.repository

import com.example.muslimway.domain.model.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

interface UserRepository{
    suspend fun saveUserInfo(userData:UserData)
}