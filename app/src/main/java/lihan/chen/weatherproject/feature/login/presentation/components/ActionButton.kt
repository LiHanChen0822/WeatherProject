package lihan.chen.weatherproject.feature.login.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lihan.chen.weatherproject.feature.core.ui.LocalSpacing

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text : String ,
    onClick : () -> Unit,
    textStyle : TextStyle = MaterialTheme.typography.bodyMedium
) {
    val spacer = LocalSpacing.current
    Button(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .padding(spacer.spaceMedium)
        ,
        onClick = {
        onClick()
    }) {
        Text(
            modifier = Modifier.padding(spacer.spaceMedium),
            text = text,
            style =  textStyle
        )
    }

}

@Preview(showSystemUi = true)
@Composable
fun ActionButtonPreview(
    modifier: Modifier = Modifier,
    text : String = "Login",
    onClick : () -> Unit = {},
    textStyle : TextStyle = MaterialTheme.typography.bodyMedium
) {
    val spacer = LocalSpacing.current
    Button(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .padding(spacer.spaceMedium),
        onClick = {
            onClick()
        }
    ) {
        Text(
            modifier = Modifier.padding(spacer.spaceMedium),
            text = text,
            style = textStyle
        )
    }

}