package com.lucasdev.proxi.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.lucasdev.proxi.auth.domain.model.RegisterModel
import com.lucasdev.proxi.auth.domain.model.response.CreateUserWithEmailResponse
import com.lucasdev.proxi.auth.domain.model.response.CreateUserWithEmailResponseSuccess
import com.lucasdev.proxi.auth.domain.model.response.CreateUserWithEmailUnknownError
import com.lucasdev.proxi.auth.domain.model.response.EmailAlreadyExistError
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {
    override suspend fun register(registerModel: RegisterModel): CreateUserWithEmailResponse {
        return try {
            auth.createUserWithEmailAndPassword(registerModel.email, registerModel.password)
                .await()
            CreateUserWithEmailResponseSuccess()
        } catch (_: FirebaseAuthUserCollisionException) {
            EmailAlreadyExistError()
        } catch (e: Exception) {
            CreateUserWithEmailUnknownError(e.message)
        }
    }
}