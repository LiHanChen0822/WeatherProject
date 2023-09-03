package lihan.chen.weatherproject.feature.weather.data.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import lihan.chen.weatherproject.feature.core.ui.UiText
import lihan.chen.weatherproject.feature.core.util.Resource
import lihan.chen.weatherproject.feature.weather.data.remote.WeatherAPI
import lihan.chen.weatherproject.feature.weather.domain.dump.DumpData
import lihan.chen.weatherproject.feature.weather.domain.model.Weather
import lihan.chen.weatherproject.feature.weather.domain.repository.WeatherRepository
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class WeatherRepositoryImplTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weatherAPI : WeatherAPI
    private lateinit var okHttpClient: OkHttpClient
    private val mockWebServer : MockWebServer = MockWebServer()
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUp() {
        okHttpClient  = OkHttpClient.Builder()
            .writeTimeout(1 , TimeUnit.SECONDS)
            .readTimeout(1 , TimeUnit.SECONDS)
            .connectTimeout(1 , TimeUnit.SECONDS)
            .build()
        weatherAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WeatherAPI::class.java)
        weatherRepository  = WeatherRepositoryImpl(weatherAPI)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getWeatherWhenSuccess() = runBlocking{
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    Gson().toJson(DumpData.weatherDump)
                )
        )
        var isSuccess = false
        var weather : Weather?=null

        weatherRepository.getWeather().collectLatest {
            if (it is Resource.Success){
                it.data?.let {
                    isSuccess = true
                    weather = it
                }
            }else if(it is Resource.Fail){
                isSuccess = false
                weather = null
            }
        }

        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(weather).isNotNull()
    }

    @Test
    fun getWeatherWhenFailed() = runBlocking{
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(401)
        )
        var isSuccess = true
        var weather : Weather?=null
        var errorMessage : UiText?=null
        weatherRepository.getWeather().collectLatest {
            if (it is Resource.Success){
                it.data?.let {
                    isSuccess = true
                    weather = it
                }
            }else if(it is Resource.Fail){
                isSuccess = false
                weather = null
                errorMessage = it.errorMessage
            }
        }

        Truth.assertThat(isSuccess).isFalse()
        Truth.assertThat(weather).isNull()
        Truth.assertThat(errorMessage).isNotNull()
        Truth.assertThat(errorMessage?.asString(context)?:"").isNotEmpty()
    }
}