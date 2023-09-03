package lihan.chen.weatherproject.feature.login.domain.repository

import lihan.chen.weatherproject.feature.login.domain.model.VerifyResult

interface LoginRepository {
    fun verifyEmail(email : String) : VerifyResult
    fun verifyPassword(password : String) : VerifyResult
}