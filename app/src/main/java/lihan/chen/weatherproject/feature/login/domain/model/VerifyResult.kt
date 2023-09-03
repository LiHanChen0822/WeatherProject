package lihan.chen.weatherproject.feature.login.domain.model

import lihan.chen.weatherproject.feature.core.ui.UiText

data class VerifyResult(
    val result : Boolean ,
    val errMessage : UiText?=null
)
