package com.example.mobiledevelopment.laba2.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobiledevelopment.R
import com.example.mobiledevelopment.laba2.ui.components.MenuButton

@Composable
fun MainMenuScreen(
    onNewGame: () -> Unit,
    onContinue: () -> Unit,
    onSettings: () -> Unit,
    modifier: Modifier = Modifier,
    canContinue: Boolean = false,
    settingsEnabled: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF253C32))
    ) {

        Image(
            bitmap = ImageBitmap.imageResource(
                R.drawable.menu_background
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
                    Color.Black.copy(alpha = 0.15f)
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 32.dp,
                    vertical = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(
                    R.drawable.logo
                ),
                contentDescription = stringResource(
                    R.string.menu_title
                ),
                modifier = Modifier
                    .widthIn(max = 340.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit,
                filterQuality = FilterQuality.None
            )

            Spacer(
                modifier = Modifier.height(48.dp)
            )

            Column(
                modifier = Modifier
                    .widthIn(max = 340.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MenuButton(
                    text = stringResource(
                        R.string.menu_new_game
                    ),
                    onClick = onNewGame
                )

                if (canContinue) {
                    MenuButton(
                        text = stringResource(
                            R.string.menu_continue
                        ),
                        onClick = onContinue
                    )
                }

                MenuButton(
                    text = stringResource(
                        R.string.menu_settings
                    ),
                    onClick = onSettings,
                    enabled = settingsEnabled
                )
            }
        }
    }
}