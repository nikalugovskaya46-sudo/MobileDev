package com.example.mobiledevelopment.laba2.ui.level4

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiledevelopment.R
import com.example.mobiledevelopment.laba2.ui.components.MenuButton
import com.example.mobiledevelopment.laba2.ui.level1.LevelCompleteDialog

private val PortalFont = FontFamily(
    Font(R.font.brusbylyajka)
)

private val PortalTextColor = Color(0xFF451F00)

@Composable
fun FourthLevelScreen(
    onBackToMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    var digits by rememberSaveable {
        mutableStateOf(PortalPuzzle.initialDigits)
    }

    var showSuccessDialog by rememberSaveable {
        mutableStateOf(false)
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val topSpace = maxHeight * 0.08f

        Image(
            bitmap = ImageBitmap.imageResource(
                R.drawable.level4_background
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.height(topSpace)
            )

            PortalPanel(
                modifier = Modifier
                    .widthIn(max = 460.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(
                        R.string.level4_title
                    ),
                    color = PortalTextColor,
                    fontFamily = PortalFont,
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Text(
                text = stringResource(
                    R.string.level4_spin
                ),
                color = Color.White,
                fontFamily = PortalFont,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 0f
                    )
                )
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                modifier = Modifier
                    .widthIn(max = 420.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(PortalPuzzle.DIGIT_COUNT) { index ->
                    DigitWheel(
                        digit = digits[index],

                        onDigitChange = { newDigit ->
                            digits = digits.mapIndexed {
                                    digitIndex,
                                    oldDigit ->

                                if (digitIndex == index) {
                                    newDigit
                                } else {
                                    oldDigit
                                }
                            }
                        },

                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(0.7f)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            MenuButton(
                text = stringResource(
                    R.string.level4_open
                ),
                modifier = Modifier
                    .widthIn(max = 360.dp)
                    .fillMaxWidth(),
                onClick = {
                    if (PortalPuzzle.isSolved(digits)) {
                        showSuccessDialog = true
                    }
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            PortalPanel(
                modifier = Modifier
                    .widthIn(max = 460.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(
                        R.string.level4_instruction
                    ),
                    color = PortalTextColor,
                    fontFamily = PortalFont,
                    fontSize = 16.sp,
                    lineHeight = 20.sp
                )
            }
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

@Composable
private fun PortalPanel(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        color = Color(0xFFFDF4DE),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 2.dp,
            color = Color(0xFF4A3503)
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}