package com.example.mobiledevelopment.laba2.ui.level1

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiledevelopment.R

@Composable
fun FirstLevelScreen(
    foundItemIds: Set<String>,
    onItemFound: (String) -> Unit,
    onBackToMenu: () -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = FirstLevelItems.items

    val foundCount = items.count { item ->
        item.id in foundItemIds
    }

    val isCompleted = foundCount == items.size

    var completionDialogClosed by rememberSaveable(isCompleted) {
        mutableStateOf(false)
    }

    val inventoryItems = listOf(
        "flashlight" to R.drawable.item_flashlight,
        "map" to R.drawable.item_map,
        "syrup" to R.drawable.item_syrup,
        "mug" to R.drawable.item_mug,
        "compass" to R.drawable.item_compass
    )

    val grayscaleFilter = remember {
        ColorFilter.colorMatrix(
            ColorMatrix().apply {
                setToSaturation(0f)
            }
        )
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val scaleX = maxWidth / FirstLevelItems.IMAGE_WIDTH
        val scaleY = maxHeight / FirstLevelItems.IMAGE_HEIGHT

        Image(
            bitmap = ImageBitmap.imageResource(
                R.drawable.level1_background
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            filterQuality = FilterQuality.None
        )

        items.forEach { item ->
            val isFound = item.id in foundItemIds

            val itemName = stringResource(item.nameRes)

            val itemState = stringResource(
                if (isFound) {
                    R.string.item_found
                } else {
                    R.string.item_not_found
                }
            )

            Box(
                modifier = Modifier
                    .offset(
                        x = scaleX * item.x,
                        y = scaleY * item.y
                    )
                    .size(
                        width = scaleX * item.width,
                        height = scaleY * item.height
                    )
                    .border(
                        width = 2.dp,
                        color = if (isFound) {
                            Color.White
                        } else {
                            Color.Transparent
                        }
                    )
                    .clickable(
                        enabled = !isFound
                    ) {
                        onItemFound(item.id)
                    }
                    .semantics {
                        contentDescription = itemName
                        stateDescription = itemState
                    }
            )
        }

        LevelPanel(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(
                    horizontal = 24.dp,
                    vertical = 8.dp
                )
        ) {
            Text(
                text = stringResource(
                    R.string.level1_title
                ),
                color = LevelTextColor,
                fontFamily = LevelFont,
                fontSize = 24.sp,
                lineHeight = 28.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .heightIn(max = maxHeight * 0.22f)
                .navigationBarsPadding()
                .padding(
                    horizontal = 20.dp,
                    vertical = 8.dp
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LevelPanel(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    inventoryItems.forEach { (id, imageRes) ->
                        val item = items.first { it.id == id }

                        Image(
                            bitmap = ImageBitmap.imageResource(
                                imageRes
                            ),
                            contentDescription = stringResource(
                                item.nameRes
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(36.dp),
                            contentScale = ContentScale.Fit,
                            filterQuality = FilterQuality.None,
                            colorFilter = if (id in foundItemIds) {
                                null
                            } else {
                                grayscaleFilter
                            }
                        )
                    }
                }
            }

            LevelPanel(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(
                        R.string.level1_instruction
                    ),
                    color = LevelTextColor,
                    fontFamily = LevelFont,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    if (isCompleted && !completionDialogClosed) {
        LevelCompleteDialog(
            onContinue = {
                completionDialogClosed = true
                onContinue()
            },
            onMainMenu = {
                completionDialogClosed = true
                onBackToMenu()
            }
        )
    }
}