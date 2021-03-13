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
package com.example.androiddevchallenge.ui.widget

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.request.ImageRequest

/**
 * This is a modified version of:
 * https://github.com/agarasul/RemoteImage/blob/main/RemoteImage
 */
private sealed class NetworkImageState {
    object Loading : NetworkImageState()

    data class Loaded(
        val image: ImageBitmap
    ) : NetworkImageState()

    object LoadError : NetworkImageState()
}

@Composable
fun CoilImage(
    context: Context,
    data: Any?,
    builder: ImageRequest.Builder.() -> Unit = {},
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.None,
    contentDescription: String = "",
    error: @Composable (() -> Unit)? = null,
    loading: @Composable (() -> Unit?)? = null
) {
    val state = loadImage(context = context, data = data, builder = builder)
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,

    ) {
        when (state) {
            is NetworkImageState.Loading -> {
                if (loading != null) {
                    loading()
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
            is NetworkImageState.Loaded -> {
                Image(
                    bitmap = state.image,
                    contentScale = contentScale,
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("loadedImg"),
                )
            }
            is NetworkImageState.LoadError -> {
                if (error != null) {
                    error()
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = "Could not load image")
                    }
                }
            }
        }
    }
}

@Composable
private fun loadImage(
    context: Context,
    builder: ImageRequest.Builder.() -> Unit = {},
    data: Any?,
): NetworkImageState {
    var state by remember(data) {
        mutableStateOf<NetworkImageState>(NetworkImageState.Loading)
    }
    if (state is NetworkImageState.Loaded) {
        return state
    }
    if (data == null) {
        state = NetworkImageState.LoadError
    } else {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(data)
            .apply(builder)
            .target(
                onError = {
                    state = NetworkImageState.LoadError
                }
            ) { drawable ->
                // Handle the result.
                val bitmap = (drawable as BitmapDrawable).bitmap.asImageBitmap()
                state = NetworkImageState.Loaded(bitmap)
            }
            .build()
        imageLoader.enqueue(request)
    }
    return state
}
