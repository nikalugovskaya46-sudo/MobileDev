package com.example.mobiledevelopment.laba1

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobiledevelopment.ui.theme.MobileDevelopmentTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.mobiledevelopment.R
import androidx.compose.foundation.layout.size


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MobileDevelopmentTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var result by remember { mutableStateOf("") }

    val analyzer = TextAnalyzer()

    val text = """
        Вода
        Ведро
        Лава
        Лавовое озеро
        Алмаз
        Алмазная кирка
        Еда
        Факел
        Блоки
        Кровать
    """.trimIndent()

    val minecraftFont = FontFamily(
        Font(R.font.minecraft)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.book_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    Box (
        modifier = Modifier
            .align(Alignment.Center)
            .size(
                width = 380.dp,
                height = 600.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.book),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 50.dp,
                end = 30.dp,
                top = 15.dp
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Найдите самые популярные первые буквы слов в данном тексте." ,
            fontFamily = minecraftFont,
            fontSize = 12.sp
        )

        Text(
            text = "Список предметов:",
            fontFamily = minecraftFont,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text = text,
            fontFamily = minecraftFont,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 10.dp)
        )

        Button(
            onClick = {
                result = analyzer.findMostPopularWords(text)
            },
            modifier = Modifier.padding(top = 20.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            )
        ) {
            Text(
                "Проверить себя",
                fontFamily = minecraftFont,
                fontSize = 12.sp,
                color = Color.White
            )
        }

        Text(
            text = result,
            fontFamily = minecraftFont,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
    }}
    }
}