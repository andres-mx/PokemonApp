package com.software.profile_module.domain

import com.software.profile_module_api.domain.EmailValidatorUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class EmailValidatorUseCaseTest {
    private lateinit var emailValidatorUseCase: EmailValidatorUseCase

    @Before
    fun setUp() {
        emailValidatorUseCase = EmailValidatorUseCaseImpl()
    }

    @Test
    fun `GIVEN an email WHEN EmailValidatorUseCase is requested THEN email value is valid`() =
        runTest {
            //Given
            //When
            val isValidEmail = emailValidatorUseCase("example@example.com")
            //Then
            assertTrue(isValidEmail)
        }

    @Test
    fun `GIVEN an email with dash WHEN EmailValidatorUseCase is requested THEN email value is valid`() =
        runTest {
            //Given
            //When
            val isValidEmail = emailValidatorUseCase("example-123@example.com")
            //Then
            assertTrue(isValidEmail)
        }

    @Test
    fun `GIVEN an email with underscore WHEN EmailValidatorUseCase is requested THEN email value is valid`() =
        runTest {
            //Given
            //When
            val isValidEmail = emailValidatorUseCase("example_123@example.com")
            //Then
            assertTrue(isValidEmail)
        }

    @Test
    fun `GIVEN an invalid email WHEN EmailValidatorUseCase is requested with an incomplete email THEN email value is invalid`() =
        runTest {
            //Given
            //When
            val isValidEmail = emailValidatorUseCase("example_123@example.")
            //Then
            assertFalse(isValidEmail)
        }

    @Test
    fun `GIVEN an invalid email WHEN EmailValidatorUseCase is requested with an empty email THEN email value is invalid`() =
        runTest {
            //Given
            //When
            val isValidEmail = emailValidatorUseCase("")
            //Then
            assertFalse(isValidEmail)
        }
}