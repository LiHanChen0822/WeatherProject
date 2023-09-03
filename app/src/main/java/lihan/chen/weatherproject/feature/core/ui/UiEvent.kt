package lihan.chen.weatherproject.feature.core.ui

sealed class UiEvent{
    object Success : UiEvent()
    data class Failed(val message : UiText?=null) :UiEvent()
}
