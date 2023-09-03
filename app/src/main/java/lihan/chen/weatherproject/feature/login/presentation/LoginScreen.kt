package lihan.chen.weatherproject.feature.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import lihan.chen.weatherproject.R
import lihan.chen.weatherproject.feature.core.ui.UiEvent
import lihan.chen.weatherproject.feature.login.presentation.components.ActionButton
import lihan.chen.weatherproject.feature.login.presentation.components.LoginBasicTextField

@Composable
fun LoginScreen(
    state : LoginState ,
    onEvent : (LoginEvent) -> Unit ,
    uiEvent: Flow<UiEvent> ,
    toNextPage : () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = context){
        uiEvent.collectLatest {
            when(it){
                is UiEvent.Success ->{
                    toNextPage()
                }else -> Unit
            }
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginBasicTextField(
            text = state.email,
            title = stringResource(id = R.string.email_title),
            hintText = stringResource(id = R.string.email_hint),
            errorText = state.emailErrorMessage?.asString(context)?:"",
            onValueChanged = {
                onEvent(
                    LoginEvent.OnEmailChanged(it)
                )
            },
            keyboardType = KeyboardType.Email
        )
        LoginBasicTextField(
            text = state.password,
            title = stringResource(id = R.string.password_title),
            hintText = stringResource(id = R.string.password_hint),
            errorText = state.passwordErrorMessage?.asString(context)?:"",
            onValueChanged = {
                onEvent(
                    LoginEvent.OnPasswordChanged(it)
                )
            },
            keyboardType = KeyboardType.Password
        )
        Row(
            modifier = Modifier.align(Alignment.End)
        ) {
            if (state.isChecking){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            ActionButton(
                text = stringResource(id = R.string.login_button_text),
                onClick = {
                    onEvent(
                        LoginEvent.OnVerifyClicked
                    )
                }
            )
        }

    }

}