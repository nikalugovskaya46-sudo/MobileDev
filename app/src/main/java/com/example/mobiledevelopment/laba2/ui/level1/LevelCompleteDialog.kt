package com.example.mobiledevelopment.laba2.ui.level1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mobiledevelopment.R
import com.example.mobiledevelopment.laba2.ui.components.MenuButton

@Composable
fun LevelCompleteDialog(
    onContinue: () -> Unit,
    onMainMenu: () -> Unit
) {
    Dialog(
        onDismissRequest = onMainMenu,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .widthIn(max = 360.dp)
                    .fillMaxWidth(),
                color = LevelPanelColor,
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = LevelBorderColor
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = stringResource(
                            R.string.level1_success_title
                        ),
                        color = LevelTextColor,
                        fontFamily = LevelFont,
                        fontSize = 32.sp,
                        lineHeight = 38.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    MenuButton(
                        text = stringResource(
                            R.string.menu_continue
                        ),
                        onClick = onContinue
                    )

                    MenuButton(
                        text = stringResource(
                            R.string.level1_home
                        ),
                        onClick = onMainMenu
                    )
                }
            }
        }
    }
}