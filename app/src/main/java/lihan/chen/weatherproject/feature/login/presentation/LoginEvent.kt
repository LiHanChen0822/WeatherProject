package lihan.chen.weatherproject.feature.login.presentation

sealed interface LoginEvent{
    data class OnEmailChanged(val email : String) : LoginEvent
    data class OnPasswordChanged(val password : String) : LoginEvent
    object OnVerifyClicked : LoginEvent
}
