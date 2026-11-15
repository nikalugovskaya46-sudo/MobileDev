package com.example.mobiledevelopment.laba2.ui.level4

object PortalPuzzle {

    const val DIGIT_COUNT = 4

    val initialDigits = listOf(
        1, 1, 1, 1
    )

    private val correctCode = listOf(
        3, 4, 1, 2
    )

    fun turnDigit(
        digit: Int,
        step: Int
    ): Int {
        return (digit + step + 10) % 10
    }

    fun isSolved(digits: List<Int>): Boolean {
        return digits == correctCode
    }
}