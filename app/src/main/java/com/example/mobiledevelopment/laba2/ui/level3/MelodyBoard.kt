package com.example.mobiledevelopment.laba2.ui.level3

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.mobiledevelopment.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun MelodyBoard(
    placedCardIds: List<String>,
    onCardPlaced: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var activeCardId by remember {
        mutableStateOf<String?>(null)
    }

    var dragPosition by remember {
        mutableStateOf(Offset.Zero)
    }

    var isSettling by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    BoxWithConstraints(
        modifier = modifier
    ) {
        val scaleX =
            constraints.maxWidth.toFloat() / MelodyPuzzle.BOARD_WIDTH

        val scaleY =
            constraints.maxHeight.toFloat() / MelodyPuzzle.BOARD_HEIGHT

        val cardWidth =
            maxWidth * (MelodyPuzzle.CARD_WIDTH / MelodyPuzzle.BOARD_WIDTH)

        val cardHeight =
            maxHeight * (MelodyPuzzle.CARD_HEIGHT / MelodyPuzzle.BOARD_HEIGHT)

        val noteWidth =
            maxWidth * (202f / MelodyPuzzle.BOARD_WIDTH)

        val noteHeight =
            maxHeight * (310f / MelodyPuzzle.BOARD_HEIGHT)

        val homeRects = MelodyPuzzle.cards.associate { card ->
            card.id to Rect(
                left = card.startX * scaleX,
                top = card.startY * scaleY,
                right = (card.startX + MelodyPuzzle.CARD_WIDTH) * scaleX,
                bottom = (card.startY + MelodyPuzzle.CARD_HEIGHT) * scaleY
            )
        }

        val slotRects = List(MelodyPuzzle.correctOrder.size) { index ->
            val y = MelodyPuzzle.SLOT_START_Y +
                    index * MelodyPuzzle.SLOT_STEP

            Rect(
                left = MelodyPuzzle.SLOT_X * scaleX,
                top = y * scaleY,
                right = (
                        MelodyPuzzle.SLOT_X + MelodyPuzzle.CARD_WIDTH
                        ) * scaleX,
                bottom = (y + MelodyPuzzle.CARD_HEIGHT) * scaleY
            )
        }

        fun finishDrag(checkPlacement: Boolean) {
            val cardId = activeCardId ?: return

            if (checkPlacement) {
                val cardCenter = dragPosition + Offset(
                    x = MelodyPuzzle.CARD_WIDTH * scaleX / 2f,
                    y = MelodyPuzzle.CARD_HEIGHT * scaleY / 2f
                )

                val slotIndex = slotRects.indexOfFirst { rect ->
                    rect.contains(cardCenter)
                }

                if (MelodyPuzzle.canPlace(cardId, slotIndex)) {
                    onCardPlaced(cardId)
                }
            }

            activeCardId = null
            isSettling = true

            scope.launch {
                delay(240)
                isSettling = false
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(
                    scaleX,
                    scaleY,
                    placedCardIds,
                    isSettling
                ) {
                    if (!isSettling) {
                        detectDragGestures(
                            onDragStart = { position ->
                                val card = MelodyPuzzle.cards
                                    .asReversed()
                                    .firstOrNull { item ->
                                        item.id !in placedCardIds &&
                                                homeRects.getValue(item.id)
                                                    .contains(position)
                                    }

                                activeCardId = card?.id

                                if (card != null) {
                                    dragPosition =
                                        homeRects.getValue(card.id).topLeft
                                }
                            },

                            onDrag = { change, dragAmount ->
                                if (activeCardId != null) {
                                    change.consume()
                                    dragPosition += dragAmount
                                }
                            },

                            onDragEnd = {
                                finishDrag(checkPlacement = true)
                            },

                            onDragCancel = {
                                finishDrag(checkPlacement = false)
                            }
                        )
                    }
                }
        ) {

            Image(
                bitmap = ImageBitmap.imageResource(
                    R.drawable.marceline_note
                ),
                contentDescription = null,
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = (12f * scaleX).roundToInt(),
                            y = (12f * scaleY).roundToInt()
                        )
                    }
                    .size(
                        width = noteWidth,
                        height = noteHeight
                    ),
                contentScale = ContentScale.Fit,
                filterQuality = FilterQuality.None
            )

            slotRects.forEach { rect ->
                Canvas(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                x = rect.left.roundToInt(),
                                y = rect.top.roundToInt()
                            )
                        }
                        .size(
                            width = cardWidth,
                            height = cardHeight
                        )
                ) {
                    val radius = CornerRadius(
                        x = 10.dp.toPx(),
                        y = 10.dp.toPx()
                    )

                    drawRoundRect(
                        color = Color.Black.copy(alpha = 0.12f),
                        cornerRadius = radius
                    )

                    drawRoundRect(
                        color = Color.White,
                        cornerRadius = radius,
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(
                                floatArrayOf(
                                    12.dp.toPx(),
                                    8.dp.toPx()
                                )
                            )
                        )
                    )
                }
            }

            MelodyPuzzle.cards.forEach { card ->
                val isDragging = activeCardId == card.id
                val isPlaced = card.id in placedCardIds

                val slotIndex =
                    MelodyPuzzle.correctOrder.indexOf(card.id)

                val restingPosition = if (isPlaced) {
                    slotRects[slotIndex].topLeft
                } else {
                    homeRects.getValue(card.id).topLeft
                }

                val targetPosition = if (isDragging) {
                    dragPosition
                } else {
                    restingPosition
                }

                val animatedPosition by animateOffsetAsState(
                    targetValue = targetPosition,
                    animationSpec = if (isDragging) {
                        snap()
                    } else {
                        tween(durationMillis = 220)
                    },
                    label = "card_${card.id}"
                )

                Image(
                    bitmap = ImageBitmap.imageResource(
                        card.imageRes
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                x = animatedPosition.x.roundToInt(),
                                y = animatedPosition.y.roundToInt()
                            )
                        }
                        .size(
                            width = cardWidth,
                            height = cardHeight
                        )
                        .zIndex(
                            if (isDragging) 2f else 1f
                        ),
                    contentScale = ContentScale.FillBounds,
                    filterQuality = FilterQuality.None
                )
            }
        }
    }
}