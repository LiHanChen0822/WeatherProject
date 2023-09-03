package lihan.chen.weatherproject.feature.login.data.repository

import android.util.Patterns
import lihan.chen.weatherproject.R
import lihan.chen.weatherproject.feature.core.ui.UiText
import lihan.chen.weatherproject.feature.login.domain.model.VerifyResult
import lihan.chen.weatherproject.feature.login.domain.repository.LoginRepository

class LoginRepositoryImpl : LoginRepository {

    override fun verifyEmail(email: String): VerifyResult {
       return when{
            email.isEmpty() ->{
                VerifyResult(
                    result = false,
                    errMessage = UiText.StringResource(R.string.email_empty_error_message)
                )
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                VerifyResult(
                    result = false,
                    errMessage = UiText.StringResource(R.string.email_format_error_message)
                )
            }
            else ->{
                VerifyResult(
                    result = true
                )
            }
        }
    }

    override fun verifyPassword(password: String): VerifyResult {
        return when{
            password.isEmpty() ->{
                VerifyResult(
                    result = false,
                    errMessage = UiText.StringResource(R.string.password_empty_error_message)
                )
            }
            password.length < 8 ->{
                VerifyResult(
                    result = false,
                    errMessage = UiText.StringResource(R.string.password_length_error_message)
                )
            }
            else ->{
                VerifyResult(
                    result = true
                )
            }
        }
    }

}