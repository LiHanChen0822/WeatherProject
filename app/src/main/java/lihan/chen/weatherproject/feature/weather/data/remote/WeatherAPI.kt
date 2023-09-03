package lihan.chen.weatherproject.feature.weather.data.remote

import lihan.chen.weatherproject.BuildConfig
import lihan.chen.weatherproject.feature.weather.data.remote.model.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    //https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001
    @GET("F-C0032-001/")
    suspend fun getWeathers(
        @Query("Authorization") authorization: String = BuildConfig.API_KEY,
        @Query("format") format: String = "JSON",
        @Query("elementName") elementName: String ="Wx,PoP,MinT,MaxT"
    ) : Response<WeatherDto>
}