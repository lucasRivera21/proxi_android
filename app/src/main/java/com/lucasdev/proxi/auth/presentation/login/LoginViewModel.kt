package com.lucasdev.proxi.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasdev.proxi.auth.data.repository.AuthRepository
import com.lucasdev.proxi.auth.domain.model.LoginModel
import com.lucasdev.proxi.auth.domain.model.error.LoginCredentialsError
import com.lucasdev.proxi.auth.domain.model.error.LoginUnknownError
import com.lucasdev.proxi.auth.domain.model.response.LoginResponseCredentialsError
import com.lucasdev.proxi.auth.domain.model.response.LoginResponseSuccess
import com.lucasdev.proxi.auth.domain.model.response.LoginResponseUnknowError
import com.lucasdev.proxi.core.isEmailValid
import com.lucasdev.proxi.core.isPasswordValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    private val _loginModel = MutableStateFlow(LoginModel())
    val loginModel = _loginModel.asStateFlow()

    fun onEmailChange(value: String) {
        _loginModel.value = _loginModel.value.copy(email = value, isEmailValid = true)
    }

    fun onPasswordChange(value: String) {
        _loginModel.value = _loginModel.value.copy(password = value, isPasswordValid = true)
    }

    fun onLoginClick(onNavigate: () -> Unit) {
        _loginModel.value = _loginModel.value.copy(isLoading = true, error = null)
        if (!isFieldsValidate()) {
            _loginModel.value = _loginModel.value.copy(isLoading = false)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val response = authRepository.login(_loginModel.value)

            withContext(Dispatchers.Main) {
                when (response) {
                    is LoginResponseCredentialsError -> _loginModel.value =
                        _loginModel.value.copy(error = LoginCredentialsError())

                    is LoginResponseSuccess -> onNavigate()
                    is LoginResponseUnknowError -> _loginModel.value =
                        _loginModel.value.copy(error = LoginUnknownError())
                }
            }

            _loginModel.value = _loginModel.value.copy(isLoading = false)
        }
    }

    private fun isFieldsValidate(): Boolean {
        _loginModel.value = _loginModel.value.copy(
            isEmailValid = isEmailValid(_loginModel.value.email),
            isPasswordValid = isPasswordValid(_loginModel.value.password)
        )

        return _loginModel.value.isEmailValid && _loginModel.value.isPasswordValid
    }
}