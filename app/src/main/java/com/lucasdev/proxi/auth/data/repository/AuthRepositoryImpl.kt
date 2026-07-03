package com.lucasdev.proxi.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.lucasdev.proxi.auth.domain.model.LoginModel
import com.lucasdev.proxi.auth.domain.model.RegisterModel
import com.lucasdev.proxi.auth.domain.model.response.CreateUserWithEmailResponse
import com.lucasdev.proxi.auth.domain.model.response.CreateUserWithEmailResponseSuccess
import com.lucasdev.proxi.auth.domain.model.response.CreateUserWithEmailUnknownError
import com.lucasdev.proxi.auth.domain.model.response.EmailAlreadyExistError
import com.lucasdev.proxi.auth.domain.model.response.LoginResponse
import com.lucasdev.proxi.auth.domain.model.response.LoginResponseCredentialsError
import com.lucasdev.proxi.auth.domain.model.response.LoginResponseSuccess
import com.lucasdev.proxi.auth.domain.model.response.LoginResponseUnknowError
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

    override suspend fun login(loginModel: LoginModel): LoginResponse {
        return try {
            auth.signInWithEmailAndPassword(loginModel.email, loginModel.password).await()
            LoginResponseSuccess()
        } catch (_: FirebaseAuthInvalidCredentialsException) {
            LoginResponseCredentialsError()
        } catch (e: Exception) {
            LoginResponseUnknowError(e.message)
        }
    }
}