package lihan.chen.weatherproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import lihan.chen.weatherproject.feature.core.navigation.Route
import lihan.chen.weatherproject.feature.login.presentation.LoginScreen
import lihan.chen.weatherproject.feature.login.presentation.LoginViewModel
import lihan.chen.weatherproject.feature.weather.presetation.WeatherScreen
import lihan.chen.weatherproject.feature.weather.presetation.WeatherViewModel
import lihan.chen.weatherproject.ui.theme.WeatherProjectTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Route.Login.route
                    ){
                        composable(route = Route.Login.route){
                            val viewModel = hiltViewModel<LoginViewModel>()
                            val state = viewModel.state.collectAsState()
                            LoginScreen(
                                state = state.value,
                                onEvent = {
                                    viewModel.onEvent(it)
                                },
                                uiEvent = viewModel.uiEvent,
                                toNextPage = {
                                    navController.navigate(
                                        route = Route.Weather.route
                                    )
                                }
                            )

                        }
                        composable(route = Route.Weather.route){
                            val viewModel  = hiltViewModel<WeatherViewModel>()
                            val state = viewModel.state.collectAsState()
                            WeatherScreen(
                                state = state.value,
                                onEvent = {
                                    viewModel.onEvent(it)
                                },
                                uiEvent = viewModel.uiEvent,
                                onNavigate = {
                                    navController.navigateUp()
                                }
                            )

                        }
                    }
                }
            }
        }
    }
}
