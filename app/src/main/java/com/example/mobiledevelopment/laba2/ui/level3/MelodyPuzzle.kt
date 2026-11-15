package com.example.mobiledevelopment.laba2.ui.level3

import com.example.mobiledevelopment.R

data class MelodyCard(
    val id: String,
    val imageRes: Int,
    val startX: Float,
    val startY: Float
)

object MelodyPuzzle {

    const val BOARD_WIDTH = 411f
    const val BOARD_HEIGHT = 760f

    const val CARD_WIDTH = 155f
    const val CARD_HEIGHT = 100f

    const val SLOT_X = 235f
    const val SLOT_START_Y = 12f
    const val SLOT_STEP = 116f

    val correctOrder = listOf(
        "moon",
        "bat",
        "heart",
        "star",
        "drop"
    )

    val cards = listOf(
        MelodyCard(
            id = "moon",
            imageRes = R.drawable.card_moon,
            startX = 25f,
            startY = 335f
        ),
        MelodyCard(
            id = "bat",
            imageRes = R.drawable.card_bat,
            startX = 76f,
            startY = 440f
        ),
        MelodyCard(
            id = "heart",
            imageRes = R.drawable.card_heart,
            startX = 25f,
            startY = 535f
        ),
        MelodyCard(
            id = "star",
            imageRes = R.drawable.card_star,
            startX = 231f,
            startY = 592f
        ),
        MelodyCard(
            id = "drop",
            imageRes = R.drawable.card_drop,
            startX = 81f,
            startY = 650f
        )
    )

    fun canPlace(
        cardId: String,
        slotIndex: Int
    ): Boolean {
        return correctOrder.getOrNull(slotIndex) == cardId
    }

    fun isSolved(placedCardIds: List<String>): Boolean {
        return correctOrder.all { it in placedCardIds }
    }
}