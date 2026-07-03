package com.lucasdev.proxi.splash.data.repository

interface SplashRepository {
    suspend fun userLogged(): Boolean
}