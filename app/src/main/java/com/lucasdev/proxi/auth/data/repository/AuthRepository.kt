package com.lucasdev.proxi.auth.data.repository

import com.lucasdev.proxi.auth.domain.model.LoginModel
import com.lucasdev.proxi.auth.domain.model.RegisterModel
import com.lucasdev.proxi.auth.domain.model.response.CreateUserWithEmailResponse
import com.lucasdev.proxi.auth.domain.model.response.LoginResponse

interface AuthRepository {
    suspend fun register(registerModel: RegisterModel): CreateUserWithEmailResponse
    suspend fun login(loginModel: LoginModel): LoginResponse
}