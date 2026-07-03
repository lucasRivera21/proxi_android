package com.lucasdev.proxi.auth.presentation.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasdev.proxi.auth.data.repository.AuthRepository
import com.lucasdev.proxi.auth.domain.model.RegisterModel
import com.lucasdev.proxi.auth.domain.model.error.RegisterEmailAlreadyExist
import com.lucasdev.proxi.auth.domain.model.error.RegisterUnknownError
import com.lucasdev.proxi.auth.domain.model.response.CreateUserWithEmailResponseSuccess
import com.lucasdev.proxi.auth.domain.model.response.CreateUserWithEmailUnknownError
import com.lucasdev.proxi.auth.domain.model.response.EmailAlreadyExistError
import com.lucasdev.proxi.core.isEmailValid
import com.lucasdev.proxi.core.isNameValid
import com.lucasdev.proxi.core.isPasswordValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    private val _registerModel = MutableStateFlow(RegisterModel())
    val registerModel = _registerModel.asStateFlow()

    fun onNameChange(value: String) {
        _registerModel.value = _registerModel.value.copy(name = value, isNameValid = true)
    }

    fun onEmailChange(value: String) {
        _registerModel.value = _registerModel.value.copy(email = value, isEmailValid = true)
    }

    fun onPasswordChange(value: String) {
        _registerModel.value = _registerModel.value.copy(password = value, isPasswordValid = true)
    }

    fun onCreateAccount(onNavigate: () -> Unit) {
        _registerModel.value = _registerModel.value.copy(isLoading = true, error = null)
        Log.d("lucas", "_registerModel.value: ${_registerModel.value}")
        if (!onValidateFields()) {
            _registerModel.value = _registerModel.value.copy(isLoading = false)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val response = authRepository.register(_registerModel.value)

            withContext(Dispatchers.Main) {
                when (response) {
                    is CreateUserWithEmailResponseSuccess -> onNavigate()

                    is CreateUserWithEmailUnknownError -> _registerModel.value =
                        _registerModel.value.copy(error = RegisterUnknownError())

                    is EmailAlreadyExistError -> _registerModel.value =
                        _registerModel.value.copy(error = RegisterEmailAlreadyExist())
                }
            }

            _registerModel.value = _registerModel.value.copy(isLoading = false)
        }
    }

    private fun onValidateFields(): Boolean {
        _registerModel.value = _registerModel.value.copy(
            isNameValid = isNameValid(_registerModel.value.name),
            isEmailValid = isEmailValid(_registerModel.value.email),
            isPasswordValid = isPasswordValid(_registerModel.value.password)
        )

        Log.d("lucas", "_registerModel.value: ${_registerModel.value}")

        return _registerModel.value.isNameValid && _registerModel.value.isEmailValid && _registerModel.value.isPasswordValid
    }
}