package com.example.muslimway.data.repository

import com.example.muslimway.domain.model.UserData
import com.example.muslimway.domain.repository.UserRepository
import com.example.muslimway.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
):UserRepository{

    override suspend fun saveUserInfo(userData: UserData) {
        val mp=HashMap<String,Any>()
        mp["email"] = userData.email
        mp["username"] = userData.username
        mp["latitude"] = userData.address.lat
        mp["longitude"] = userData.address.long
        mp["city"] = userData.address.city
        firebaseAuth.uid?.let { uid->
            firestore.collection(Constants.USERS)
                .document(uid)
                .set(mp)
        }

    }

}