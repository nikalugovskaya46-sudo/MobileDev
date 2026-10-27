package com.example.mobiledevelopment.laba2.ui.level3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun ThirdLevelScreen(
    onBackToMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    var placedCardIds by rememberSaveable {
        mutableStateOf(emptyList<String>())
    }

    var showSuccessDialog by rememberSaveable {
        mutableStateOf(false)
    }

    val isCompleted = MelodyPuzzle.isSolved(placedCardIds)

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(
                R.drawable.level3_background
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
                .padding(
                    horizontal = 12.dp,
                    vertical = 12.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .widthIn(max = 480.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFDF4DE),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = Color(0xFF4A3503)
                )
            ) {
                Text(
                    text = stringResource(
                        R.string.level3_title
                    ),
                    modifier = Modifier.padding(12.dp),
                    color = Color(0xFF451F00),
                    fontFamily = FontFamily(
                        Font(R.font.brusbylyajka)
                    ),
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            MelodyBoard(
                placedCardIds = placedCardIds,
                onCardPlaced = { cardId ->
                    if (cardId !in placedCardIds) {
                        placedCardIds = placedCardIds + cardId
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .widthIn(max = 480.dp)
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            MenuButton(
                text = stringResource(
                    R.string.level3_play
                ),
                enabled = isCompleted,
                onClick = {
                    showSuccessDialog = true
                },
                modifier = Modifier
                    .widthIn(max = 340.dp)
                    .fillMaxWidth()
            )
        }
    }

    if (showSuccessDialog) {
        LevelCompleteDialog(
            onContinue = {
                showSuccessDialog = false
            },
            onMainMenu = {
                showSuccessDialog = false
                onBackToMenu()
            }
        )
    }
}