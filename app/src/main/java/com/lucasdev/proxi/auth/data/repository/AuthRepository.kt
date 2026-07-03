package com.lucasdev.proxi.auth.data.repository

import com.lucasdev.proxi.auth.domain.model.RegisterModel
import com.lucasdev.proxi.auth.domain.model.response.CreateUserWithEmailResponse

interface AuthRepository {
    suspend fun register(registerModel: RegisterModel): CreateUserWithEmailResponse
}