package com.vtx.widgetos.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF697585),
    onPrimary = Color.White,
    secondary = Color(0xFF9AA3AE),
    onSecondary = Color(0xFF20242A),
    background = Canvas,
    onBackground = TextPrimary,
    surface = SurfaceWhite,
    onSurface = TextPrimary,
    surfaceVariant = AccentSoft,
    onSurfaceVariant = TextSecondary,
    outline = SilverLight
)

@Composable
fun VtxTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = MaterialTheme.typography,
        content = content
    )
}
