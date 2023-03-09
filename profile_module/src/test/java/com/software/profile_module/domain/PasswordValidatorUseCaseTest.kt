package com.software.profile_module.domain

import com.software.profile_module_api.domain.PasswordValidatorUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PasswordValidatorUseCaseTest {
    private lateinit var passwordValidatorUseCase: PasswordValidatorUseCase

    @Before
    fun setUp() {
        passwordValidatorUseCase = PasswordValidatorUseCaseImpl()
    }

    @Test
    fun `GIVEN an password WHEN PasswordValidatorUseCase is requested THEN password values is valid`() =
        runTest {
            //Given
            //When
            val isValidPassword = passwordValidatorUseCase("Casita1?")
            //Then
            assertTrue(isValidPassword)
        }

    @Test
    fun `GIVEN an password with underscore WHEN PasswordValidatorUseCase is requested THEN password values is valid`() =
        runTest {
            //Given
            //When
            val isValidPassword = passwordValidatorUseCase("Casita_1")
            //Then
            assertTrue(isValidPassword)
        }

    @Test
    fun `GIVEN an password with dash WHEN PasswordValidatorUseCase is requested THEN password values is valid`() =
        runTest {
            //Given
            //When
            val isValidPassword = passwordValidatorUseCase("Casita-1")
            //Then
            assertTrue(isValidPassword)
        }

    @Test
    fun `GIVEN an invalid password WHEN PasswordValidatorUseCase is requested THEN password length is wrong`() =
        runTest {
            //Given
            //When
            val isValidPassword = passwordValidatorUseCase("Cas")
            //Then
            assertFalse(isValidPassword)
        }

    @Test
    fun `GIVEN an invalid password WHEN PasswordValidatorUseCase is requested THEN password without character special`() =
        runTest {
            //Given
            //When
            val isValidPassword = passwordValidatorUseCase("Casita1")
            //Then
            assertFalse(isValidPassword)
        }

    @Test
    fun `GIVEN an invalid password WHEN PasswordValidatorUseCase is requested THEN password without number`() =
        runTest {
            //Given
            //When
            val isValidPassword = passwordValidatorUseCase("Casita-_-")
            //Then
            assertFalse(isValidPassword)
        }
}