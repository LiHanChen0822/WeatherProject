package lihan.chen.weatherproject.feature.core.ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val spaceDefault : Dp = 0.dp,
    val spaceSmall : Dp = 4.dp,
    val spaceMedium : Dp = 8.dp,
    val spaceLarge : Dp = 16.dp,
    val spaceExtraLarge : Dp = 20.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }
