package com.example.mobiledevelopment.laba2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobiledevelopment.R
import com.example.mobiledevelopment.laba2.ui.localization.localizedStringResource

@Composable
fun LevelHeader(
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        IconButton(
            onClick = onHomeClick,
            modifier = Modifier
                .align(Alignment.End)
                .size(48.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_home),
                contentDescription = localizedStringResource(R.string.level1_home),
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            propagateMinConstraints = true
        ) {
            title()
        }
    }
}