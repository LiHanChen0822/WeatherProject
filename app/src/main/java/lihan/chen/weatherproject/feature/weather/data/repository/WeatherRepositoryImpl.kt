package lihan.chen.weatherproject.feature.weather.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lihan.chen.weatherproject.R
import lihan.chen.weatherproject.feature.core.ui.UiText
import lihan.chen.weatherproject.feature.core.util.Resource
import lihan.chen.weatherproject.feature.weather.data.remote.WeatherAPI
import lihan.chen.weatherproject.feature.weather.domain.mapper.toWeather
import lihan.chen.weatherproject.feature.weather.domain.model.Weather
import lihan.chen.weatherproject.feature.weather.domain.repository.WeatherRepository
import java.io.IOException

class WeatherRepositoryImpl(
    private val weatherAPI: WeatherAPI
) : WeatherRepository {

    override fun getWeather(): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading())
        try {
            val result = weatherAPI.getWeathers()
            if (result.isSuccessful){
                result.body()?.let {
                    emit(
                        Resource.Success(
                            data = it.toWeather()
                        )
                    )
                }
            }else{
                emit(
                    Resource.Fail(
                        data = null,
                        errorMessage = UiText.StringResource(
                            R.string.weather_api_failed_unknown
                        )
                    )
                )
            }
        }catch (e : IOException){
            emit(
                Resource.Fail(
                    data = null,
                    errorMessage = UiText.DynamicString(
                        e.message.toString()
                    )
                )
            )
        }
    }
}