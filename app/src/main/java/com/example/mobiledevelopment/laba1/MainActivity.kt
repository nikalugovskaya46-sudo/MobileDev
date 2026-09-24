package com.example.mobiledevelopment.laba1

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Найдите самые популярные первые буквы слов в данном тексте."
        )

        Button(
            onClick = {
                result = "Результат"
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text("Проверить себя")
        }

        Text(
            text = result,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}