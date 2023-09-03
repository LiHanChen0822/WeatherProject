package lihan.chen.weatherproject.feature.weather.presetation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lihan.chen.weatherproject.feature.core.sharepreference.MSharedPreference
import lihan.chen.weatherproject.feature.core.ui.UiEvent
import lihan.chen.weatherproject.feature.core.util.Resource
import lihan.chen.weatherproject.feature.weather.domain.repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val sharedPreference: MSharedPreference
) : ViewModel(){

    private val _state = MutableStateFlow(WeatherState())
    var state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    var uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(WeatherEvent.SendApi)
    }

    fun onEvent(event: WeatherEvent){
        when(event){
            is WeatherEvent.Logout->{
                sharedPreference.clearAll()
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
            is WeatherEvent.SendApi->{
                viewModelScope.launch {
                    weatherRepository.getWeather().collectLatest { resource ->
                        when(resource){
                            is Resource.Loading->{
                                _state.update {
                                    it.copy(
                                        isLoading = true,
                                        errorMessage = null
                                    )
                                }
                            }
                            is Resource.Success->{
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        weather = resource.data,
                                        errorMessage = null
                                    )
                                }
                            }
                            is Resource.Fail->{
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = resource.errorMessage
                                    )
                                }
                                _uiEvent.send(
                                    UiEvent.Failed(resource.errorMessage)
                                )
                            }
                        }
                    }
                }
            }
        }
    }



}