package com.example.mobiledevelopment.laba2.ui.level4

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiledevelopment.R
import kotlin.math.abs

@Composable
fun DigitWheel(
    digit: Int,
    onDigitChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var rollDirection by remember {
        mutableStateOf(1)
    }

    val currentDigit by rememberUpdatedState(digit)
    val currentOnDigitChange by rememberUpdatedState(onDigitChange)

    fun turn(step: Int) {
        rollDirection = step

        currentOnDigitChange(
            PortalPuzzle.turnDigit(
                digit = currentDigit,
                step = step
            )
        )
    }

    BoxWithConstraints(
        modifier = modifier
            .clickable {
                turn(step = 1)
            }
            .pointerInput(Unit) {
                var dragDistance = 0f

                detectVerticalDragGestures(
                    onDragStart = {
                        dragDistance = 0f
                    },

                    onVerticalDrag = { change, dragAmount ->
                        change.consume()
                        dragDistance += dragAmount
                    },

                    onDragEnd = {
                        if (abs(dragDistance) >= 24.dp.toPx()) {
                            val step = if (dragDistance < 0f) {
                                1
                            } else {
                                -1
                            }

                            turn(step)
                        }

                        dragDistance = 0f
                    },

                    onDragCancel = {
                        dragDistance = 0f
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        val digitFontSize = (maxWidth.value * 0.55f).sp

        Image(
            bitmap = ImageBitmap.imageResource(
                R.drawable.digit_cell
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            filterQuality = FilterQuality.None
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 6.dp,
                    vertical = 12.dp
                )
                .clipToBounds(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = digit,
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                transitionSpec = {
                    val direction = rollDirection

                    slideInVertically(
                        animationSpec = tween(160),
                        initialOffsetY = { height ->
                            height * direction
                        }
                    ) togetherWith slideOutVertically(
                        animationSpec = tween(160),
                        targetOffsetY = { height ->
                            -height * direction
                        }
                    )
                },
                label = "digit"
            ) { value ->
                Text(
                    text = value.toString(),
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.brusbylyajka)
                    ),
                    fontSize = digitFontSize,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}