package lihan.chen.weatherproject.feature.weather.domain.mapper

import lihan.chen.weatherproject.feature.weather.data.remote.model.WeatherDto
import lihan.chen.weatherproject.feature.weather.domain.model.Weather

fun WeatherDto.toWeather() : Weather{
    return Weather(
        records = records
    )
}