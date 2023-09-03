package lihan.chen.weatherproject.feature.weather.presetation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lihan.chen.weatherproject.R
import lihan.chen.weatherproject.feature.core.ConstData.STRING_NA_EMPTY
import lihan.chen.weatherproject.feature.core.ui.LocalSpacing
import lihan.chen.weatherproject.feature.weather.data.remote.model.Location
import lihan.chen.weatherproject.feature.weather.domain.dump.DumpData

@Composable
fun WeatherItem(
    location : Location,
    modifier : Modifier = Modifier
) {

    val spacer = LocalSpacing.current
    Column(
        modifier = modifier
            .padding(spacer.spaceMedium)
            .clip(RoundedCornerShape(50.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(50.dp)
            )
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(50.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = location.locationName,
            style = MaterialTheme.typography.displaySmall
        )
        val weatherElement = location.weatherElement
        val wx = weatherElement.find { it.elementName == "Wx" }
        val pop = weatherElement.find { it.elementName == "PoP" }
        val minT = weatherElement.find { it.elementName == "MinT" }
        val maxT = weatherElement.find { it.elementName == "MaxT" }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //12h / 24h / 36h
            (0..2).forEach { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(5f)
                        .padding(spacer.spaceMedium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val startTime = formatTime(wx?.time?.get(index)?.startTime)
                    val endTime = formatTime(wx?.time?.get(index)?.endTime)
                    val rain = pop?.time?.get(index)?.parameter?.parameterName?: STRING_NA_EMPTY
                    val weatherIcon = getResIdByParameterValue(wx?.time?.get(index)?.parameter?.parameterValue?:"")
                    val weatherInfo = wx?.time?.get(index)?.parameter?.parameterName?:STRING_NA_EMPTY
                    val temperatureMax = maxT?.time?.get(index)?.parameter?.parameterName?: STRING_NA_EMPTY
                    val temperatureMin = minT?.time?.get(index)?.parameter?.parameterName?: STRING_NA_EMPTY
                    Column(
                        modifier = Modifier.weight(2f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.size(60.dp),
                            painter = painterResource(id = weatherIcon),
                            contentDescription = "WeatherIcon"
                        )
                        Text(
                            text =weatherInfo,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.icon_weather_rain),
                            contentDescription ="rain %"
                        )
                        Text(text = "$rain %")
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.icon_weather_temperture_max),
                            contentDescription ="temperature"
                        )
                        Column {
                            Text(text = "$temperatureMax")
                            Text(text = "$temperatureMin")
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column {
                            Text(text = "$startTime")
                            Text(text = "$endTime")
                        }
                    }
                }
            }

        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun WeatherItemPreview(
    location : Location = DumpData.weatherDump.location[0],
    modifier : Modifier = Modifier
) {

    val spacer = LocalSpacing.current
    Column(
        modifier = modifier
            .padding(spacer.spaceMedium)
            .clip(RoundedCornerShape(50.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(50.dp)
            )
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(50.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = location.locationName,
            style = MaterialTheme.typography.displaySmall
        )
        val weatherElement = location.weatherElement
        val wx = weatherElement.find { it.elementName == "Wx" }
        val pop = weatherElement.find { it.elementName == "PoP" }
        val minT = weatherElement.find { it.elementName == "MinT" }
        val maxT = weatherElement.find { it.elementName == "MaxT" }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //12h / 24h / 36h
            (0..2).forEach { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(5f)
                        .padding(spacer.spaceMedium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val startTime = formatTime(wx?.time?.get(index)?.startTime)
                    val endTime = formatTime(wx?.time?.get(index)?.endTime)
                    val rain = pop?.time?.get(index)?.parameter?.parameterName?: STRING_NA_EMPTY
                    val weatherIcon = getResIdByParameterValue(wx?.time?.get(index)?.parameter?.parameterValue?:"")
                    val weatherInfo = wx?.time?.get(index)?.parameter?.parameterName?:STRING_NA_EMPTY
                    val temperatureMax = maxT?.time?.get(index)?.parameter?.parameterName?: STRING_NA_EMPTY
                    val temperatureMin = minT?.time?.get(index)?.parameter?.parameterName?: STRING_NA_EMPTY
                    Column(
                        modifier = Modifier.weight(2f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.size(60.dp),
                            painter = painterResource(id = weatherIcon),
                            contentDescription = "WeatherIcon"
                        )
                        Text(
                            text =weatherInfo,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.icon_weather_rain),
                            contentDescription ="rain %"
                        )
                        Text(text = "$rain %")
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.icon_weather_temperture_max),
                            contentDescription ="temperature"
                        )
                        Column {
                            Text(text = "$temperatureMax")
                            Text(text = "$temperatureMin")
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column {
                            Text(text = "$startTime")
                            Text(text = "$endTime")
                        }
                    }
                }
            }

        }
    }

}
fun formatTime(time : String?) : String {
    if (time == null) return STRING_NA_EMPTY
    if (time.trim().isEmpty()) return  STRING_NA_EMPTY
    //2023-09-03 00:00:00
    //09-03 00:00:00
    //09-03 00
    //09-03-00
    return time.substringAfter("-").replace(":00:00","").replace(" ","-")
}

fun getResIdByParameterValue(parameterValue: String): Int{
    return when(parameterValue){
        "1" -> R.drawable.icon_weather_clear
        "2","3" -> R.drawable.icon_weather_mostly_clear
        "4" -> R.drawable.icon_weather_partly_cloudy
        "5","6" -> R.drawable.icon_weather_mostly_cloudy
        "7" -> R.drawable.icon_weather_cloudy
        "8","30" -> R.drawable.icon_weather_cloudy_rainy
        "9","10","12","13" -> R.drawable.icon_weather_partly_cloudy_rainy
        "11","14"-> R.drawable.icon_weather_cloudy_rainy_day
        "15"-> R.drawable.icon_weather_rainy_thunder
        "16","17","18","34"-> R.drawable.icon_weather_cloudy_local_rainy_thunder
        "19","20"-> R.drawable.icon_weather_sun_rainy
        "21","22","33"-> R.drawable.icon_weather_sun_rainy_thunder
        "23" -> R.drawable.icon_weather_cloudy_local_rainy_snow
        "24","25" -> R.drawable.icon_weather_sun_fog
        "26","27" -> R.drawable.icon_weather_sun_cloudy_fog
        "28" -> R.drawable.icon_weather_cloudy_fog
        "29" -> R.drawable.icon_weather_cloudy_local_rainy
        "31" -> R.drawable.icon_weather_partly_cloudy_rainy_fog
        "32","38","39" -> R.drawable.icon_weather_cloudy_rainy_fog
        "35" -> R.drawable.icon_weather_partly_partly_cloudy_local_rainy_thunder_fog
        "36","41" -> R.drawable.icon_weather_partly_cloudy_local_rainy_thunder
        "37" -> R.drawable.icon_weather_cloudy_local_rainy_snow_fog
        "42" -> R.drawable.icon_weather_snow
        else ->{ R.drawable.icon_weather_na }
    }
}