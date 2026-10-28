package com.example.mobiledevelopment.laba2.ui.level2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiledevelopment.R
import com.example.mobiledevelopment.laba2.ui.components.MenuButton
import com.example.mobiledevelopment.laba2.ui.level1.LevelCompleteDialog

private val TextColor = Color(0xFF451F00)
private val BorderColor = Color(0xFF4A3503)
private val PanelColor = Color(0xFFFDF4DE)
private val GridColor = Color(0xFF92004E)
private val GridBorderColor = Color(0xFFFEBC04)

private val PixelFont = FontFamily(
    Font(R.font.brusbylyajka)
)

@Composable
fun SecondLevelScreen(
    onBackToMenu: () -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    var rotations by rememberSaveable {
        mutableStateOf(PipesPuzzle.initialRotations)
    }

    var showError by rememberSaveable {
        mutableStateOf(false)
    }

    var showSuccessDialog by rememberSaveable {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(
                R.drawable.level2_background
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.None
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PipePanel(
                modifier = Modifier
                    .widthIn(max = 420.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(
                        R.string.level2_title
                    ),
                    color = TextColor,
                    fontFamily = PixelFont,
                    fontSize = 28.sp,
                    lineHeight = 34.sp,
                    textAlign = TextAlign.Center
                )
            }

            BoxWithConstraints(
                modifier = Modifier
                    .widthIn(max = 380.dp)
                    .fillMaxWidth()
            ) {
                val arrowWidth = 24.dp
                val boardSize = maxWidth - arrowWidth

                Text(
                    text = "→",
                    modifier = Modifier
                        .width(arrowWidth)
                        .offset(
                            y = boardSize / 6 - 18.dp
                        ),
                    color = Color.White,
                    fontSize = 32.sp,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier.padding(
                        start = arrowWidth
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(GridColor)
                            .border(
                                width = 2.dp,
                                color = GridBorderColor
                            )
                    ) {
                        repeat(PipesPuzzle.SIZE) { row ->
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {
                                repeat(PipesPuzzle.SIZE) { column ->
                                    val index =
                                        row * PipesPuzzle.SIZE + column

                                    val imageRes = when (
                                        PipesPuzzle.types[index]
                                    ) {
                                        PipeType.STRAIGHT ->
                                            R.drawable.pipe_straight

                                        PipeType.CORNER ->
                                            R.drawable.pipe_corner
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .border(
                                                width = 1.dp,
                                                color = GridBorderColor
                                            )
                                            .clickable {
                                                rotations =
                                                    rotations.mapIndexed {
                                                            itemIndex,
                                                            rotation ->

                                                        if (itemIndex == index) {
                                                            (rotation + 1) % 4
                                                        } else {
                                                            rotation
                                                        }
                                                    }

                                                showError = false
                                            }
                                    ) {
                                        Image(
                                            bitmap =
                                                ImageBitmap.imageResource(
                                                    imageRes
                                                ),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .graphicsLayer {
                                                    rotationZ =
                                                        rotations[index] * 90f
                                                },
                                            contentScale =
                                                ContentScale.FillBounds,
                                            filterQuality =
                                                FilterQuality.None
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Text(
                        text = "↓",
                        modifier = Modifier
                            .align(Alignment.End)
                            .width(boardSize / 3),
                        color = Color.White,
                        fontSize = 32.sp,
                        lineHeight = 36.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            MenuButton(
                text = stringResource(
                    R.string.level2_check
                ),
                modifier = Modifier
                    .widthIn(max = 340.dp)
                    .fillMaxWidth(),
                onClick = {
                    val solved = PipesPuzzle.isSolved(rotations)

                    showError = !solved
                    showSuccessDialog = solved
                }
            )

            PipePanel(
                modifier = Modifier
                    .widthIn(max = 420.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(
                        R.string.level2_instruction
                    ),
                    color = TextColor,
                    fontFamily = PixelFont,
                    fontSize = 16.sp,
                    lineHeight = 22.sp
                )
            }
        }
    }

    if (showSuccessDialog) {
        LevelCompleteDialog(
            onContinue = {
                showSuccessDialog = false
                onContinue()
            },
            onMainMenu = {
                showSuccessDialog = false
                onBackToMenu()
            }
        )
    }
}

@Composable
private fun PipePanel(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        color = PanelColor,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 2.dp,
            color = BorderColor
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = content
        )
    }
}