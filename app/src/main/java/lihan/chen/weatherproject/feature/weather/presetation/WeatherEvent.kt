package lihan.chen.weatherproject.feature.weather.presetation

sealed interface WeatherEvent{
    object Logout : WeatherEvent
    object SendApi : WeatherEvent
}