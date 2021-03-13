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

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color


@Stable
class BloomColors(
    buttonText1: Color,
    buttonText2: Color,
    buttonBackground: Color,
    h1: Color,
    h2: Color,
    body1: Color,
    body2: Color,
    caption: Color,
    subtitle: Color,
    divider: Color,
) {
    var buttonText1 by mutableStateOf(buttonText1, structuralEqualityPolicy())
        internal set
    var buttonText2 by mutableStateOf(buttonText2, structuralEqualityPolicy())
        internal set
    var buttonBackground by mutableStateOf(buttonBackground, structuralEqualityPolicy())
        internal set
    var h1 by mutableStateOf(h1, structuralEqualityPolicy())
        internal set
    var h2 by mutableStateOf(h2, structuralEqualityPolicy())
        internal set
    var body1 by mutableStateOf(body1, structuralEqualityPolicy())
        internal set
    var body2 by mutableStateOf(body2, structuralEqualityPolicy())
        internal set
    var caption by mutableStateOf(caption, structuralEqualityPolicy())
        internal set
    var subtitle by mutableStateOf(subtitle, structuralEqualityPolicy())
        internal set
    var divider by mutableStateOf(divider, structuralEqualityPolicy())
        internal set
}
