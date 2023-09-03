package lihan.chen.weatherproject.feature.login.presentation

import lihan.chen.weatherproject.feature.core.ui.UiText

data class LoginState(
    val email : String = "",
    val emailErrorMessage : UiText?=null,
    val password : String = "",
    val passwordErrorMessage : UiText?=null,
    val isChecking : Boolean = false
)
