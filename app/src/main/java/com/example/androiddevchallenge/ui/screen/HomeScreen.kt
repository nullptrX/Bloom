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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.entity.PlantEntity
import com.example.androiddevchallenge.ui.model.BloomModel
import com.example.androiddevchallenge.ui.screen.home.HomePiece
import com.example.androiddevchallenge.ui.widget.BottomBar
import com.example.androiddevchallenge.ui.widget.BottomBarData
import com.example.androiddevchallenge.ui.widget.Pager
import com.example.androiddevchallenge.ui.widget.PagerState


val themeDatas = listOf(
    PlantEntity(name = "Desert chic", url = R.drawable.img_desert_chic),
    PlantEntity(name = "Tiny terrariums", url = R.drawable.img_tiny_terrariums),
    PlantEntity(name = "Jungle vibes", url = R.drawable.img_jungle_vibes),
    PlantEntity(name = "Easy care", url = R.drawable.img_easy_care),
    PlantEntity(name = "Statements", url = R.drawable.img_statements),
)

val gardenDatas = listOf(
    PlantEntity(name = "Monstera", url = R.drawable.img_monstera),
    PlantEntity(name = "Aglaonema", url = R.drawable.img_aglaonema),
    PlantEntity(name = "Peace lily", url = R.drawable.img_peace_lily),
    PlantEntity(name = "Fiddle leaf tree", url = R.drawable.img_fiddle_leaf_tree),
    PlantEntity(name = "Snake plant", url = R.drawable.img_snake_plant),
    PlantEntity(name = "Pothos", url = R.drawable.img_pothos),

    )

@Composable
fun HomeScreen() {
    val model = viewModel<BloomModel>()
    Content(model = model)
}

@Composable
private fun Content(model: BloomModel) {
    var currentIndex by remember { mutableStateOf(0) }
    val modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    Column(modifier = modifier) {
        Pager(
            modifier = Modifier.weight(1f),
            state = PagerState(currentPage = 0, maxPage = 3)
        ) {
            when (currentIndex) {
                0 -> HomePiece()
                else -> HomePiece()
            }
        }
        val home = Icons.Default.Home
        val favoriteBorder = Icons.Default.FavoriteBorder
        val accountCircle = Icons.Default.AccountCircle
        val shoppingCart = Icons.Default.ShoppingCart
        BottomBar(data = listOf(
            BottomBarData("Home", home),
            BottomBarData("Favorites", favoriteBorder),
            BottomBarData("Profile", accountCircle),
            BottomBarData("Cart", shoppingCart),

            ), index = 0, onChanged = {
            currentIndex = it
        })
    }
}
