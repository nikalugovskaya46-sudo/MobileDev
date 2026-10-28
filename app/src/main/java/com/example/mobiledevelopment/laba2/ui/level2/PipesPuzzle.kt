package com.example.mobiledevelopment.laba2.ui.level2

enum class PipeType {
    STRAIGHT,
    CORNER
}

object PipesPuzzle {

    const val SIZE = 3

    val types = listOf(
        PipeType.STRAIGHT,
        PipeType.STRAIGHT,
        PipeType.CORNER,

        PipeType.CORNER,
        PipeType.STRAIGHT,
        PipeType.CORNER,

        PipeType.CORNER,
        PipeType.STRAIGHT,
        PipeType.CORNER
    )

    val initialRotations = listOf(
        1, 1, 1,
        3, 0, 3,
        1, 0, 3
    )

    private fun getOpenings(
        index: Int,
        rotation: Int
    ): List<Int> {
        val initialOpenings = when (types[index]) {

            PipeType.STRAIGHT -> listOf(1, 3)

            PipeType.CORNER -> listOf(0, 1)
        }

        return initialOpenings.map { direction ->
            (direction + rotation) % 4
        }
    }

    fun isSolved(rotations: List<Int>): Boolean {
        if (rotations.size != types.size) {
            return false
        }

        val visited = mutableSetOf<Int>()

        var currentIndex = 0

        var incomingDirection = 3

        while (visited.add(currentIndex)) {
            val openings = getOpenings(
                index = currentIndex,
                rotation = rotations[currentIndex]
            )

            if (incomingDirection !in openings) {
                return false
            }

            val outgoingDirection = openings.first {
                it != incomingDirection
            }

            if (
                currentIndex == types.lastIndex &&
                outgoingDirection == 2
            ) {
                return visited.size == types.size
            }

            var nextRow = currentIndex / SIZE
            var nextColumn = currentIndex % SIZE

            when (outgoingDirection) {
                0 -> nextRow--
                1 -> nextColumn++
                2 -> nextRow++
                3 -> nextColumn--
            }

            if (
                nextRow !in 0 until SIZE ||
                nextColumn !in 0 until SIZE
            ) {
                return false
            }

            currentIndex = nextRow * SIZE + nextColumn

            incomingDirection = (outgoingDirection + 2) % 4
        }


        return false
    }
}