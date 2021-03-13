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
package com.example.androiddevchallenge.ui.screen.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.entity.PlantEntity
import com.example.androiddevchallenge.ui.theme.BloomTheme
import com.example.androiddevchallenge.ui.theme.elevations
import com.example.androiddevchallenge.ui.widget.CoilImage

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
fun HomePiece() {
    val state = rememberScrollState(0)
    val modifier = Modifier
        .background(MaterialTheme.colors.background)
        .verticalScroll(state = state)
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(16.dp))
        ContentTop()
        ContentCenter()
        ContentBottom()
    }
}

@Composable
private fun ContentTop() {
    var search by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "",
            )
        },
        label = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.body1,
                color = BloomTheme.colors.body1,
            )
        },
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = BloomTheme.colors.body1,
        ),

        singleLine = true,
        value = search,
        onValueChange = {
            search = it
        }
    )
}

@Composable
private fun ContentCenter() {

    val state = rememberScrollState(0)
    Text(
        modifier = Modifier
            .paddingFromBaseline(top = 32.dp)
            .padding(horizontal = 16.dp),
        text = "Browse themes",
        style = MaterialTheme.typography.h1,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .horizontalScroll(state = state)
            .padding(horizontal = 16.dp)
    ) {
        themeDatas.forEachIndexed { index, entity ->
            ThemeItem(entity = entity)
            if (index < themeDatas.size - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
private fun ThemeItem(entity: PlantEntity) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 8.dp),
        elevation = elevations.card,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.background,
        shape = MaterialTheme.shapes.small,
    ) {
        Column(
            modifier = Modifier
                .size(width = 136.dp, height = 136.dp)
        ) {

            CoilImage(
                context = LocalContext.current.applicationContext,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                data = entity.url,
                contentScale = ContentScale.Crop,
                contentDescription = entity.name,
            )
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f),
            ) {

                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterStart),
                    text = entity.name,
                    style = MaterialTheme.typography.h2,
                    color = BloomTheme.colors.h2,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun ContentBottom() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp)
                .weight(1f),
            text = "Design your home garden",
            style = MaterialTheme.typography.h1,
        )
        val filterList = Icons.Default.FilterList
        Icon(
            modifier = Modifier.paddingFromBaseline(top = 40.dp),
            imageVector = filterList,
            contentDescription = "",
        )
    }
    Spacer(modifier = Modifier.height(8.dp))

    val checkedIndexes = remember { mutableListOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        gardenDatas.forEachIndexed { index, entity ->
            var checked by remember { mutableStateOf(checkedIndexes.contains(index)) }
            GardenItem(
                checked = checked,
                entity = entity,
                onCheckedChange = {
                    checked = it
                    if (it) {
                        checkedIndexes.add(index)
                    } else {
                        checkedIndexes.remove(index)
                    }
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun GardenItem(
    checked: Boolean = false,
    entity: PlantEntity,
    onCheckedChange: ((Boolean) -> Unit)?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        CoilImage(
            context = LocalContext.current.applicationContext,
            modifier = Modifier
                .aspectRatio(1f, matchHeightConstraintsFirst = true),
            data = entity.url,
            contentScale = ContentScale.Crop,
            contentDescription = entity.name,
            builder = {
                size(64.dp.value.toInt())
            }
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
        ) {
            Text(
                modifier = Modifier.paddingFromBaseline(top = 24.dp),
                text = entity.name,
                style = MaterialTheme.typography.h2,
                color = BloomTheme.colors.h2,
            )
            Text(
                text = entity.description,
                style = MaterialTheme.typography.body1,
                color = BloomTheme.colors.body1,
            )
            Spacer(modifier = Modifier.height(24.dp))

            val dividerColor = BloomTheme.colors.divider
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BloomTheme.colors.divider)
            ) {
                drawLine(
                    dividerColor,
                    Offset.Zero,
                    Offset(size.width, 0f),
                    strokeWidth = size.height,
                )
            }
        }

        Checkbox(
            modifier = Modifier.align(Alignment.CenterVertically),
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreviewHomeScreen() {
    BloomTheme {
        HomePiece()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreviewHomeScreen() {
    BloomTheme(BloomTheme.Theme.Dark) {
        HomePiece()
    }
}
