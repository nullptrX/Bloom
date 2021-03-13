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
package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.common.Screen
import com.example.androiddevchallenge.ui.model.BloomModel
import com.example.androiddevchallenge.ui.model.bloomViewModel
import com.example.androiddevchallenge.ui.theme.BloomTheme
import com.example.androiddevchallenge.ui.theme.shapes

@Composable
fun WelcomeScreen() {
    val model = viewModel<BloomModel>()
    Content(model)
}

@Composable
private fun Content(model: BloomModel) {
    val modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.primary)

    Box(modifier = modifier) {
        ContentBelow()
        ContentTop(model)
    }
}

@Composable
private fun ContentBelow() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(
            id = if (MaterialTheme.colors.isLight) R.drawable.ic_light_welcome_bg
            else R.drawable.ic_dark_welcome_bg
        ),
        contentDescription = null,
    )
}

@Composable
private fun ContentTop(model: BloomModel) {

    val modifier = Modifier
        .fillMaxWidth()
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .align(Alignment.End)
                .clickable {
                    if (BloomTheme.Theme.Light == model.theme) {
                        model.theme = BloomTheme.Theme.Dark
                    } else {
                        model.theme = BloomTheme.Theme.Light
                    }
                },
            imageVector = Icons.Default.ChangeCircle, contentDescription = "",
            colorFilter = ColorFilter.tint(BloomTheme.colors.buttonBackground),
        )
        Spacer(modifier = Modifier.height(72.dp))
        Image(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.End)
                .absoluteOffset(x = 32.dp),
            painter = painterResource(
                id = if (MaterialTheme.colors.isLight) R.drawable.ic_light_welcome_illos
                else R.drawable.ic_dark_welcome_illos,
            ),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(48.dp))
        Image(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            painter = painterResource(
                id = if (MaterialTheme.colors.isLight) R.drawable.ic_light_logo
                else R.drawable.ic_dark_logo
            ),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
                .paddingFromBaseline(top = 32.dp),
            text = "Beautiful home garden solutions",
            style = MaterialTheme.typography.subtitle1,
            color = BloomTheme.colors.subtitle,
        )
        Spacer(modifier = Modifier.height(32.dp))

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = BloomTheme.colors.buttonBackground,
                contentColor = BloomTheme.colors.buttonText1,
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 8.dp),
            shape = shapes.large,
            onClick = {
            },
        ) {
            Text(
                text = "Create account",
                style = MaterialTheme.typography.button,
                color = BloomTheme.colors.buttonText1,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.Transparent,
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
            shape = shapes.large,
            onClick = {
                model.push(Screen.login)
            },
        ) {
            Text(
                text = "Log in",
                style = MaterialTheme.typography.button,
                color = BloomTheme.colors.buttonText2,
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightWelcomeScreenPreview() {
    BloomTheme(theme = BloomTheme.Theme.Light) {
        Content(bloomViewModel)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkWelcomeScreenPreview() {
    BloomTheme(theme = BloomTheme.Theme.Dark) {
        Content(bloomViewModel)
    }
}
