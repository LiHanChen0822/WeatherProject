package lihan.chen.weatherproject.feature.login.presentation

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lihan.chen.weatherproject.feature.core.sharepreference.MSharedPreference
import lihan.chen.weatherproject.feature.core.ui.UiEvent
import lihan.chen.weatherproject.feature.login.domain.repository.LoginRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sharedPreferences: MSharedPreference
) : ViewModel(){

    private val _state = MutableStateFlow(LoginState())
    var state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    var uiEvent = _uiEvent.receiveAsFlow()

    init {
        if (sharedPreferences.getEmail().isNotEmpty()){
            viewModelScope.launch {
                _uiEvent.send(UiEvent.Success)
            }
        }
    }
    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.OnEmailChanged->{
                _state.update {
                    it.copy(
                        email =  event.email
                    )
                }
            }
            is LoginEvent.OnPasswordChanged->{
                _state.update {
                    it.copy(
                        password =  event.password
                    )
                }
            }
            is LoginEvent.OnVerifyClicked->{
                _state.update {
                    it.copy(
                        isChecking = true
                    )
                }
                viewModelScope.launch {
                    delay(1000L)
                    val emailVerify = loginRepository.verifyEmail(_state.value.email)
                    val passwordVerify = loginRepository.verifyPassword(_state.value.password)
                    val result = listOf(
                        emailVerify,
                        passwordVerify,
                    )
                    val hasError = result.any {
                        !it.result
                    }
                    if (hasError){
                        _state.update {
                            it.copy(
                                emailErrorMessage = emailVerify.errMessage,
                                passwordErrorMessage = passwordVerify.errMessage,
                                isChecking = false
                            )
                        }
                    }else{
                        _state.update {
                            it.copy(
                                emailErrorMessage = null,
                                passwordErrorMessage = null,
                                isChecking = false
                            )
                        }
                        sharedPreferences.setEmail(_state.value.email)
                        sharedPreferences.setPassword(_state.value.password)
                        _uiEvent.send(UiEvent.Success)
                    }
                }
            }
        }
    }

}