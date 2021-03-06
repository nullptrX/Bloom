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
package com.example.androiddevchallenge.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.common.Screen
import com.example.androiddevchallenge.ui.theme.BloomTheme

class BloomModel : ViewModel() {
    companion object {
        private val screenInit = Screen.welcome
    }

    var theme by mutableStateOf(BloomTheme.Theme.Light)

    var screen by mutableStateOf(screenInit)
        private set

    private val queue: ArrayDeque<Screen> = ArrayDeque(listOf(Screen.welcome))

    fun push(screen: Screen) {
        this.queue.addFirst(screen)
        this.screen = screen
    }

    fun pop(): Boolean {
        if (this.queue.isNotEmpty()) {
            this.queue.removeFirst()
        }
        if (this.queue.isNotEmpty()) {
            this.screen = this.queue.first()
            return true
        }
        return false
    }
}
