package lihan.chen.weatherproject.feature.weather.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lihan.chen.weatherproject.BuildConfig
import lihan.chen.weatherproject.feature.weather.data.remote.WeatherAPI
import lihan.chen.weatherproject.feature.weather.data.repository.WeatherRepositoryImpl
import lihan.chen.weatherproject.feature.weather.domain.repository.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Singleton
    @Provides
    fun provideWeatherAPI() : WeatherAPI {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        weatherAPI: WeatherAPI
    ) : WeatherRepository {
        return WeatherRepositoryImpl(weatherAPI)
    }


}