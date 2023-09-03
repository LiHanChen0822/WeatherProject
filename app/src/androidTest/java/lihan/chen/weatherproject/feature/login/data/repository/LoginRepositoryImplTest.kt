package lihan.chen.weatherproject.feature.login.data.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import lihan.chen.weatherproject.feature.login.domain.repository.LoginRepository
import org.junit.Assert.*
import lihan.chen.weatherproject.R
import org.junit.Before
import org.junit.Test

class LoginRepositoryImplTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        loginRepository = LoginRepositoryImpl()
    }

    @Test
    fun verifyEmailWhenEmpty() {
        val userInput = ""
        val result = loginRepository.verifyEmail(userInput)
        Truth.assertThat(result.result).isFalse()
        Truth.assertThat(
            result.errMessage?.asString(context) == context.getString(R.string.email_empty_error_message)
        ).isTrue()
    }

    @Test
    fun verifyEmailWhenFormatError() {
        val userInput = "123abcgmail.com"
        val result = loginRepository.verifyEmail(userInput)
        Truth.assertThat(result.result).isFalse()
        Truth.assertThat(
            result.errMessage?.asString(context) == context.getString(R.string.email_format_error_message)
        ).isTrue()
    }

    @Test
    fun verifyEmailWhenSuccess() {
        val userInput = "abcdef@gmail.com"
        val result = loginRepository.verifyEmail(userInput)
        Truth.assertThat(result.result).isTrue()
        Truth.assertThat(
            result.errMessage?.asString(context) == context.getString(R.string.email_empty_error_message)
        ).isFalse()
    }

    @Test
    fun verifyPasswordWhenEmpty() {
        val userInput = ""
        val result = loginRepository.verifyPassword(userInput)
        Truth.assertThat(result.result).isFalse()
        Truth.assertThat(
            result.errMessage?.asString(context) == context.getString(R.string.password_empty_error_message)
        ).isTrue()
    }

    @Test
    fun verifyPasswordWhenLength8() {
        val userInput = "1234567"
        val result = loginRepository.verifyPassword(userInput)
        Truth.assertThat(result.result).isFalse()
        Truth.assertThat(
            result.errMessage?.asString(context) == context.getString(R.string.password_length_error_message)
        ).isTrue()
    }

    @Test
    fun verifyPasswordWhenSuccess() {
        val userInput = "12345678"
        val result = loginRepository.verifyPassword(userInput)
        Truth.assertThat(result.result).isTrue()
        Truth.assertThat(
            result.errMessage?.asString(context) == context.getString(R.string.password_length_error_message)
        ).isFalse()
    }
}