package com.example.androiddevchallenge.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
class Elevations(
    val card: Dp,
    val snackBar: Dp,
    val bottomNavigation: Dp,
)

val elevations = Elevations(
    card = 1.dp,
    snackBar = 2.dp,
    bottomNavigation = 16.dp,
)
