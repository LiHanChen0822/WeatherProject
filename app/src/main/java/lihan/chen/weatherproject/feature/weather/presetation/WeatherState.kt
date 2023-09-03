package lihan.chen.weatherproject.feature.weather.presetation

import lihan.chen.weatherproject.feature.core.ui.UiText
import lihan.chen.weatherproject.feature.weather.domain.model.Weather

data class WeatherState(
    val weather : Weather?=null,
    val isLoading : Boolean = false,
    val errorMessage : UiText?=null
)
