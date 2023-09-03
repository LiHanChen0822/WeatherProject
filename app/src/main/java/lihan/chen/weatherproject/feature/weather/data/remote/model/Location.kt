package lihan.chen.weatherproject.feature.weather.data.remote.model

data class Location(
    val locationName: String,
    val weatherElement: List<WeatherElement>
)