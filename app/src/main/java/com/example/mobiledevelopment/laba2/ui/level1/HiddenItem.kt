package com.example.mobiledevelopment.laba2.ui.level1

import com.example.mobiledevelopment.R

data class HiddenItem(
    val id: String,
    val nameRes: Int,
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float
)

object FirstLevelItems {

    const val IMAGE_WIDTH = 411f
    const val IMAGE_HEIGHT = 891f

    val items = listOf(
        HiddenItem(
            id = "compass",
            nameRes = R.string.item_compass,
            x = 50f,
            y = 430f,
            width = 47f,
            height = 52f
        ),
        HiddenItem(
            id = "flashlight",
            nameRes = R.string.item_flashlight,
            x = 200f,
            y = 135f,
            width = 67f,
            height = 53f
        ),
        HiddenItem(
            id = "mug",
            nameRes = R.string.item_mug,
            x = 30f,
            y = 268f,
            width = 53f,
            height = 63f
        ),
        HiddenItem(
            id = "map",
            nameRes = R.string.item_map,
            x = 220f,
            y = 585f,
            width = 188f,
            height = 95f
        ),
        HiddenItem(
            id = "syrup",
            nameRes = R.string.item_syrup,
            x = 346f,
            y = 296f,
            width = 48f,
            height = 66f
        )
    )
}