/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

@Stable
object BloomTheme {

    val colors: BloomColors
        @Composable
        get() = LocalBloomColors.current

    enum class Theme {
        Light, Dark,
    }
}

private val LocalBloomColors = compositionLocalOf {
    LightBloomColors
}


private val LightBloomColors = BloomColors(
    subtitle = gray,
    buttonText1 = white,
    buttonText2 = pink900,
    buttonBackground = pink900,
    h1 = gray,
    h2 = gray,
    body1 = gray,
    body2 = gray,
    caption = gray,
    divider = gray,
)

private val DarkBloomColors = BloomColors(
    subtitle = white,
    buttonText1 = gray,
    buttonText2 = white,
    buttonBackground = green300,
    h1 = white,
    h2 = white,
    body1 = white,
    body2 = white,
    caption = white,
    divider = white,
)


private val LightColorPalette = lightColors(
    primary = pink100,
    secondary = pink900,
    background = white,
    surface = white850,
    onPrimary = gray,
    onSecondary = white,
    onBackground = gray,
    onSurface = gray,
)


private val DarkColorPalette = darkColors(
    primary = green900,
    secondary = green300,
    background = gray,
    surface = white150,
    onPrimary = white,
    onSecondary = gray,
    onBackground = white,
    onSurface = white850,
)


@Composable
fun BloomTheme(
    theme: BloomTheme.Theme = BloomTheme.Theme.Light,
    content: @Composable() () -> Unit
) {
    val targetColors = when (theme) {
        BloomTheme.Theme.Dark -> DarkColorPalette
        else -> LightColorPalette
    }
    val bloomColors = when (theme) {
        BloomTheme.Theme.Dark -> DarkBloomColors
        else -> LightBloomColors
    }
    val spec: AnimationSpec<Color> = TweenSpec(600)
    val primary by animateColorAsState(targetColors.primary, spec)
    val secondary by animateColorAsState(targetColors.secondary, spec)
    val background by animateColorAsState(targetColors.background, spec)
    val surface by animateColorAsState(targetColors.surface, spec)
    val onPrimary by animateColorAsState(targetColors.onPrimary, spec)
    val onSecondary by animateColorAsState(targetColors.onSecondary, spec)
    val onBackground by animateColorAsState(targetColors.onBackground, spec)
    val onSurface by animateColorAsState(targetColors.onSurface, spec)

    val colors = Colors(
        primary = primary,
        primaryVariant = primary,
        secondary = secondary,
        secondaryVariant = secondary,
        background = background,
        surface = surface,
        onPrimary = onPrimary,
        onSecondary = onSecondary,
        onBackground = onBackground,
        onSurface = onSurface,
        error = targetColors.error,
        onError = targetColors.onError,
        isLight = targetColors.isLight
    )

    CompositionLocalProvider(LocalBloomColors provides bloomColors) {
        MaterialTheme(
            colors = colors,
            typography = typographyForBloom,
            shapes = shapes,
            content = content
        )
    }

}
