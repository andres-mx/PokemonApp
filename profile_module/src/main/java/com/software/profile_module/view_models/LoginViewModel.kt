package com.software.profile_module.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.profile_module.views.LoginIntent
import com.software.profile_module.views.LoginState
import com.software.profile_module_api.domain.EmailValidatorUseCase
import com.software.profile_module_api.domain.PasswordValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val emailValidatorUseCase: EmailValidatorUseCase,
    private val passwordValidatorUseCase: PasswordValidatorUseCase
) :
    ViewModel() {
    val userIntent = Channel<LoginIntent>(Channel.UNLIMITED)
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> get() = _loginState
    private val validEmail = MutableStateFlow(false)
    private val validPassword = MutableStateFlow(false)

    init {
        handleIntent()
    }

    val formIsValid = combine(
        validEmail, validPassword
    ) { validEmail, validPassword ->
        validEmail and validPassword
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { loginIntent ->
                when (loginIntent) {
                    is LoginIntent.IsValidEmail -> isValidEmail(loginIntent.email)
                    is LoginIntent.IsValidPassword -> isValidPassword(loginIntent.password)
                }
            }
        }
    }

    private suspend fun isValidEmail(email: String) {
        viewModelScope.launch {
            _loginState.value = try {
                validEmail.value = emailValidatorUseCase(email)
                LoginState.IsValidEmail(validEmail.value)
            } catch (e: Exception) {
                LoginState.Error(e.message)
            }
        }
    }

    private suspend fun isValidPassword(password: String) {
        viewModelScope.launch {
            _loginState.value = try {
                validPassword.value = passwordValidatorUseCase(password)
                LoginState.IsValidPassword(validPassword.value)
            } catch (e: Exception) {
                LoginState.Error(e.message)
            }
        }
    }
}