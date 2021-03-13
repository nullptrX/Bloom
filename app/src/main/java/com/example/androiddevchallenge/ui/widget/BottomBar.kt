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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.BloomTheme
import com.example.androiddevchallenge.ui.theme.tint

data class BottomBarData(
    val title: String,
    val iconId: ImageVector,
)

@Composable
fun BottomBar(data: List<BottomBarData>, index: Int, onChanged: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colors.primary),
    ) {

        data.forEachIndexed { i, bottomBarData ->
            BottomItem(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onChanged(i) },
                title = bottomBarData.title,
                vector = bottomBarData.iconId,
                tint = if (index == i) BloomTheme.colors.caption else tint,
            )
        }
    }
}

@Composable
fun BottomItem(
    modifier: Modifier = Modifier,
    vector: ImageVector,
    title: String,
    tint: Color
) {
    Column(
        modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(vector, null, Modifier.size(24.dp), tint = tint)
        Text(title, style = MaterialTheme.typography.caption, color = tint)
    }
}

@Preview
@Composable
fun LightPreviewBottomBar() {
    BloomTheme {
        val home = Icons.Default.Home
        val favoriteBorder = Icons.Default.FavoriteBorder
        val accountCircle = Icons.Default.AccountCircle
        val shoppingCart = Icons.Default.ShoppingCart
        BottomBar(
            data = listOf(
                BottomBarData("Home", home),
                BottomBarData("Favorites", favoriteBorder),
                BottomBarData("Profile", accountCircle),
                BottomBarData("Cart", shoppingCart),

                ),
            index = 0,
            onChanged = {
            }
        )
    }
}

@Preview
@Composable
fun DarkPreviewBottomBar() {
    BloomTheme(BloomTheme.Theme.Dark) {
        val home = Icons.Default.Home
        val favoriteBorder = Icons.Default.FavoriteBorder
        val accountCircle = Icons.Default.AccountCircle
        val shoppingCart = Icons.Default.ShoppingCart
        BottomBar(
            data = listOf(
                BottomBarData("Home", home),
                BottomBarData("Favorites", favoriteBorder),
                BottomBarData("Profile", accountCircle),
                BottomBarData("Cart", shoppingCart),

                ),
            index = 0,
            onChanged = {
            }
        )
    }
}
