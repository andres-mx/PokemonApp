package com.software.profile_module.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.profile_module.views.LoginIntent
import com.software.profile_module.views.LoginState
import com.software.profile_module_api.domain.EmailValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val emailValidatorUseCase: EmailValidatorUseCase) :
    ViewModel() {
    val userIntent = Channel<LoginIntent>(Channel.UNLIMITED)
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> get() = _loginState

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { loginIntent ->
                when (loginIntent) {
                    is LoginIntent.IsValidEmail -> isValidEmail(loginIntent.email)
                }
            }
        }
    }

    private suspend fun isValidEmail(email: String) {
        viewModelScope.launch {
            _loginState.value = try {
                val isValid = emailValidatorUseCase(email)
                LoginState.IsValidEmail(isValid)
            } catch (e: Exception) {
                LoginState.Error(e.message)
            }
        }
    }
}