package com.software.profile_module.domain

import com.software.profile_module_api.domain.PasswordValidatorUseCase
import javax.inject.Inject

class PasswordValidatorUseCaseImpl @Inject constructor() : PasswordValidatorUseCase {
    override suspend fun invoke(password: String): Boolean = if (password.isEmpty()) {
        false
    } else {
        password.matches(PASSWORD_REGEX.toRegex())
    }
}

const val PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*[@#\$%^&+=()¿?¡!ç.,_\\-|·¢∞¬÷≠´*¨;:]).{8,16}\$"