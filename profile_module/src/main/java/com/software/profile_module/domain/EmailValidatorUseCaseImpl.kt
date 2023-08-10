package com.software.profile_module.domain

import com.software.profile_module_api.domain.EmailValidatorUseCase
import javax.inject.Inject

class EmailValidatorUseCaseImpl @Inject constructor() : EmailValidatorUseCase {
    override suspend fun invoke(email: String): Boolean = if (email.isEmpty()) {
        false
    } else {
        email.matches(EMAIL_REGEX.toRegex())
    }
}

const val EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"