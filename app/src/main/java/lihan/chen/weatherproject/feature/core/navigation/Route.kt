package lihan.chen.weatherproject.feature.core.navigation

sealed class Route(val route : String){
    object Login : Route("Login")
    object Weather : Route("Weather")
}
