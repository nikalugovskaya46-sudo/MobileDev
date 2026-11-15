package com.example.mobiledevelopment.laba2.ui.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiledevelopment.R
import com.example.mobiledevelopment.laba2.ui.components.MenuButton

private val SettingsFont = FontFamily(Font(R.font.brusbylyajka))
private val SettingsPanelColor = Color(0xFFFDF4DE)
private val SettingsBorderColor = Color(0xFF4A3503)
private val SettingsTextColor = Color(0xFF451F00)

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onLanguageClick: () -> Unit = {},
    onMusicClick: () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.menu_background),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.None
        )

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            val logoTop = 12.dp
            val logoWidth = (maxWidth * 0.95f).coerceAtMost(420.dp)
            val logoHeight = (maxHeight * 0.24f).coerceIn(96.dp, 200.dp)
            val panelWidth = (maxWidth * 0.82f).coerceAtMost(360.dp)
            val gapBeforePanel = (maxHeight * 0.32f - logoTop - logoHeight)
                .coerceAtLeast(16.dp)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = logoTop, bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.logo),
                    contentDescription = stringResource(R.string.menu_title),
                    modifier = Modifier
                        .width(logoWidth)
                        .height(logoHeight),
                    contentScale = ContentScale.Fit,
                    filterQuality = FilterQuality.None
                )

                Spacer(modifier = Modifier.height(gapBeforePanel))

                Surface(
                    modifier = Modifier.width(panelWidth),
                    shape = RoundedCornerShape(18.dp),
                    color = SettingsPanelColor,
                    border = BorderStroke(3.dp, SettingsBorderColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 28.dp, end = 16.dp, bottom = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.menu_settings),
                            color = SettingsTextColor,
                            fontFamily = SettingsFont,
                            fontSize = 32.sp,
                            lineHeight = 38.sp,
                            textAlign = TextAlign.Center
                        )

                        SettingsOption(
                            label = stringResource(R.string.settings_language),
                            value = stringResource(R.string.settings_language_code),
                            buttonWidth = 94.dp,
                            onClick = onLanguageClick
                        )

                        SettingsOption(
                            label = stringResource(R.string.settings_music),
                            value = stringResource(R.string.settings_music_on),
                            buttonWidth = 116.dp,
                            onClick = onMusicClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsOption(
    label: String,
    value: String,
    buttonWidth: Dp,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            color = SettingsTextColor,
            fontFamily = SettingsFont,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            textAlign = TextAlign.Center
        )

        MenuButton(
            text = value,
            onClick = onClick,
            modifier = Modifier.width(buttonWidth)
        )
    }
}