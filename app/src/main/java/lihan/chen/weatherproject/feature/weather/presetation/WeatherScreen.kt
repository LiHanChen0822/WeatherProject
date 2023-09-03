package lihan.chen.weatherproject.feature.weather.presetation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import lihan.chen.weatherproject.R
import lihan.chen.weatherproject.feature.core.ui.LocalSpacing
import lihan.chen.weatherproject.feature.core.ui.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    state : WeatherState,
    onEvent : (WeatherEvent) -> Unit ,
    uiEvent : Flow<UiEvent> ,
    onNavigate : () -> Unit
) {
    val spacer = LocalSpacing.current
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(key1 = true){
            uiEvent.collectLatest {
                when(it){
                    is UiEvent.Failed->{
                        Toast.makeText(
                            context,
                            it.message?.asString(context)?:"",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is UiEvent.Success->{
                        onNavigate()
                    }
                }
            }
        }
        if (state.isLoading){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }else{
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.weather_title))
                },
                actions = {
                    IconButton(onClick = {
                        onEvent(WeatherEvent.Logout)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Go To LoginPage"
                        )
                    }
                }
            )
            if (state.errorMessage != null){
                Button(
                    onClick = {
                        onEvent(WeatherEvent.SendApi)
                }) {
                    Text(text = stringResource(id = R.string.weather_retry_button_text))
                }
            }else{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(spacer.spaceMedium)
                ){
                    state.weather?.let {
                        val data = it.records?.location?:listOf()
                        items(data){ location ->
                            WeatherItem(
                                modifier = Modifier.fillMaxWidth(),
                                location =location
                            )
                        }
                    }
                }
            }
        }
    }

}