package com.example.mobiledevelopment.laba2.ui.comic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun ComicScreen(
    imageRes: Int,
    buttonText: String,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundRes: Int = R.drawable.menu_background,
    titleRes: Int = R.string.comic_morning,
    buttonEnabled: Boolean = true
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.None
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(titleRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                color = Color.White,
                fontFamily = FontFamily(
                    Font(R.font.brusbylyajka)
                ),
                fontSize = 28.sp,
                lineHeight = 34.sp,
                textAlign = TextAlign.Center
            )

            Image(
                bitmap = ImageBitmap.imageResource(imageRes),
                contentDescription = stringResource(
                    R.string.comic_description
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit,
                filterQuality = FilterQuality.None
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                MenuButton(
                    text = buttonText,
                    onClick = onNext,
                    enabled = buttonEnabled,
                    modifier = Modifier.width(144.dp)
                )
            }
        }
    }
}