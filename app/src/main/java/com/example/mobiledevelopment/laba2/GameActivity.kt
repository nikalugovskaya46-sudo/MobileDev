package com.example.mobiledevelopment.laba2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.mobiledevelopment.R
import com.example.mobiledevelopment.laba2.data.ProgressStorage
import com.example.mobiledevelopment.laba2.ui.comic.ComicScreen
import com.example.mobiledevelopment.laba2.ui.menu.MainMenuScreen

class GameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val progressStorage = ProgressStorage(
            applicationContext
        )


        val introPages = listOf(
            R.drawable.intro_1,
            R.drawable.intro_2,
            R.drawable.intro_3
        )

        setContent {
            MaterialTheme {


                var currentScreen by rememberSaveable {
                    mutableStateOf("menu")
                }


                var comicPage by rememberSaveable {
                    mutableStateOf(0)
                }


                BackHandler(
                    enabled = currentScreen == "comic"
                ) {
                    if (comicPage > 0) {
                        comicPage--
                    } else {
                        currentScreen = "menu"
                    }
                }

                when (currentScreen) {

                    "menu" -> {
                        MainMenuScreen(
                            onNewGame = {
                                comicPage = 0
                                currentScreen = "comic"
                            },

                            onContinue = {

                            },

                            onSettings = {},

                            canContinue = progressStorage
                                .isFirstLevelCompleted(),

                            settingsEnabled = false
                        )
                    }

                    "comic" -> {
                        val isLastPage =
                            comicPage == introPages.lastIndex

                        ComicScreen(
                            imageRes = introPages[comicPage],

                            buttonText = stringResource(
                                if (isLastPage) {
                                    R.string.back_to_menu
                                } else {
                                    R.string.comic_next
                                }
                            ),

                            onNext = {
                                if (isLastPage) {
                                    currentScreen = "menu"
                                } else {
                                    comicPage++
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}