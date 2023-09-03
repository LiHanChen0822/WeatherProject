package lihan.chen.weatherproject.feature.core.util

import lihan.chen.weatherproject.feature.core.ui.UiText

sealed class Resource<T>(val data : T?=null , val errorMessage : UiText?=null){
    class Success<T>(data: T?) : Resource<T>(data)
    class Fail<T>(data: T? = null , errorMessage: UiText) : Resource<T>(data,errorMessage)
    class Loading<T> : Resource<T>()
}
