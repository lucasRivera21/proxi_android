package com.lucasdev.proxi.splash.data.repository

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : SplashRepository {
    override suspend fun userLogged(): Boolean {
        val currentUser = auth.currentUser
        return currentUser != null
    }
}