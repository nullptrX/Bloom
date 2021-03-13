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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.entity.PlantEntity
import com.example.androiddevchallenge.ui.model.BloomModel
import com.example.androiddevchallenge.ui.screen.home.HomePiece
import com.example.androiddevchallenge.ui.theme.BloomTheme
import com.example.androiddevchallenge.ui.widget.BottomBar
import com.example.androiddevchallenge.ui.widget.BottomBarData
import com.example.androiddevchallenge.ui.widget.Pager
import com.example.androiddevchallenge.ui.widget.PagerState

private val bottomBarData = listOf(
    BottomBarData("Home", Icons.Default.Home),
    BottomBarData("Favorites", Icons.Default.FavoriteBorder),
    BottomBarData("Profile", Icons.Default.AccountCircle),
    BottomBarData("Cart", Icons.Default.ShoppingCart),
)

@Composable
fun HomeScreen() {
    val modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    Column(modifier = modifier) {
        val pageState = remember { PagerState(maxPage = 3) }
        Pager(
            modifier = Modifier.weight(1f),
            state = pageState
        ) {
            when (page) {
                0 -> HomePiece()
                else -> Box {}
            }
        }
        BottomBar(
            data = bottomBarData,
            index = pageState.currentPage,
            onChanged = {
                pageState.currentPage = it
            }
        )
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreviewHomeScreen() {
    BloomTheme {
        HomeScreen()
    }
}


@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreviewHomeScreen() {
    BloomTheme(BloomTheme.Theme.Dark) {
        HomeScreen()
    }
}