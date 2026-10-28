package com.example.mobiledevelopment.laba2.ui.result

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun ResultScreen(
    onMainMenu: () -> Unit,
    onRestart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(
                R.drawable.level4_background
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.None
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(alpha = 0.35f)
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .widthIn(max = 340.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFDF4DE),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = Color(0xFF4A3503)
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = stringResource(
                            R.string.ending_title
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFF451F00),
                        fontFamily = FontFamily(
                            Font(R.font.brusbylyajka)
                        ),
                        fontSize = 32.sp,
                        lineHeight = 32.sp,
                        textAlign = TextAlign.Center
                    )

                    MenuButton(
                        text = stringResource(
                            R.string.ending_replay
                        ),
                        onClick = onRestart
                    )

                    MenuButton(
                        text = stringResource(
                            R.string.ending_home
                        ),
                        onClick = onMainMenu
                    )
                }
            }
        }
    }
}