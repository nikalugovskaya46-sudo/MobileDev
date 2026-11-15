package com.example.mobiledevelopment.laba2.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiledevelopment.R

private val OutlineFont = FontFamily(Font(R.font.brusbylyajka))

@Composable
fun OutlinedText(
    text: String,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 4.dp
) {
    val strokePx = with(LocalDensity.current) { strokeWidth.toPx() }

    val textStyle = TextStyle(
        fontFamily = OutlineFont,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )

    val outlineStyle = textStyle.merge(
        TextStyle(drawStyle = Stroke(width = strokePx))
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.clearAndSetSemantics { }) {
            Text(
                text = text,
                color = Color.Black,
                style = outlineStyle,
                maxLines = 1,
                softWrap = false
            )
        }

        Text(
            text = text,
            color = Color.White,
            style = textStyle,
            maxLines = 1,
            softWrap = false
        )
    }
}