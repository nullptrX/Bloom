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
package com.example.androiddevchallenge

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.common.Screen
import com.example.androiddevchallenge.ui.model.BloomModel
import com.example.androiddevchallenge.ui.screen.HomeScreen
import com.example.androiddevchallenge.ui.screen.LoginScreen
import com.example.androiddevchallenge.ui.screen.WelcomeScreen
import com.example.androiddevchallenge.ui.theme.BloomTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloomTheme {
                BloomApp(window)
            }
        }
    }

    override fun onBackPressed() {
        val model by viewModels<BloomModel>()
        if (!model.pop()) {
            super.onBackPressed()
        }
    }
}

// Start building your app here!
@Composable
fun BloomApp(window: Window? = null) {

    var statusBarColor = MaterialTheme.colors.background
    var navigationBarColor = MaterialTheme.colors.background
    val model: BloomModel = viewModel()
    if (model.screen == Screen.welcome) {
        statusBarColor = MaterialTheme.colors.primary
        navigationBarColor = MaterialTheme.colors.primary
    } else if (model.screen == Screen.home) {
        navigationBarColor = MaterialTheme.colors.primary
    }
    window?.also {

        it.statusBarColor = statusBarColor.toArgb()
        it.navigationBarColor = navigationBarColor.toArgb()

        @Suppress("DEPRECATION")
        if (statusBarColor.luminance() > 0.5f) {
            it.decorView.systemUiVisibility = it.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        @Suppress("DEPRECATION")
        if (statusBarColor.luminance() > 0.5f) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                it.decorView.systemUiVisibility = it.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }
        }
    }
    Surface(color = MaterialTheme.colors.surface) {

        when (model.screen) {
            Screen.welcome -> WelcomeScreen()
            Screen.login -> LoginScreen()
            Screen.home -> HomeScreen()
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    BloomTheme {
        BloomApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    BloomTheme(BloomTheme.Theme.Dark) {
        BloomApp()
    }
}
