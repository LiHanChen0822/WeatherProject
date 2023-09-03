package lihan.chen.weatherproject.feature.login.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lihan.chen.weatherproject.feature.core.ui.LocalSpacing

@Composable
fun LoginBasicTextField(
    modifier: Modifier = Modifier,
    title : String,
    text : String ,
    hintText : String,
    errorText : String,
    onValueChanged : (String) ->Unit,
    textStyle : TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardType : KeyboardType
) {
    val spacer = LocalSpacing.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacer.spaceMedium)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(spacer.spaceSmall))
        //BasicTextField
        Box{
            BasicTextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(spacer.spaceLarge)
                    .fillMaxWidth()
                    .padding(spacer.spaceSmall)
                ,
                value = text,
                onValueChange = {
                    onValueChanged(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                ),
                textStyle = textStyle,
                maxLines = 1,
                singleLine = true
            )
            if (text.trim().isEmpty()){
                Text(
                    modifier = Modifier.padding(
                        spacer.spaceExtraLarge
                    ),
                    text = hintText ,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )
            }

        }

        Spacer(modifier = Modifier.height(spacer.spaceSmall))
        if (errorText.isNotEmpty()){
            Text(
                text = errorText,
                style = textStyle.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
        }

    }


}

@Preview(showSystemUi = true)
@Composable
fun LoginBasicTextFieldPreview(
    modifier: Modifier = Modifier,
    title : String = "Email",
    text : String ="",
    hintText : String = "example@gmail.com",
    errorText : String = "Error",
    onValueChanged : (String) ->Unit = {},
    textStyle : TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardType : KeyboardType = KeyboardType.Email
) {
    val spacer = LocalSpacing.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacer.spaceMedium)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(spacer.spaceSmall))
        //BasicTextField
        Box{
            BasicTextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(spacer.spaceLarge)
                    .fillMaxWidth()
                    .padding(spacer.spaceSmall)
                ,
                value = text,
                onValueChange = {
                    onValueChanged(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                ),
                textStyle = textStyle,
                maxLines = 1,
                singleLine = true
            )
            if (text.trim().isEmpty()){
                Text(
                    modifier = Modifier.padding(
                        spacer.spaceExtraLarge
                    ),
                    text = hintText ,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )
            }

        }

        Spacer(modifier = Modifier.height(spacer.spaceSmall))
        if (errorText.isNotEmpty()){
            Text(
                text = errorText,
                style = textStyle.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
        }

    }


}