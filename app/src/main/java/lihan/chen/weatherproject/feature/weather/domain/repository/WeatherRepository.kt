package lihan.chen.weatherproject.feature.weather.domain.repository

import kotlinx.coroutines.flow.Flow
import lihan.chen.weatherproject.feature.core.util.Resource
import lihan.chen.weatherproject.feature.weather.domain.model.Weather

interface WeatherRepository {
    fun getWeather() : Flow<Resource<Weather>>
}