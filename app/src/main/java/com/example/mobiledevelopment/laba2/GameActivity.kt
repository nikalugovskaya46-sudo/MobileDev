package com.example.mobiledevelopment.laba2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.mobiledevelopment.R
import com.example.mobiledevelopment.laba2.data.ProgressStorage
import com.example.mobiledevelopment.laba2.ui.comic.ComicScreen
import com.example.mobiledevelopment.laba2.ui.level1.FirstLevelItems
import com.example.mobiledevelopment.laba2.ui.level1.FirstLevelScreen
import com.example.mobiledevelopment.laba2.ui.menu.MainMenuScreen
import com.example.mobiledevelopment.laba2.ui.level2.SecondLevelScreen
import com.example.mobiledevelopment.laba2.ui.level3.ThirdLevelScreen

class GameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val progressStorage = ProgressStorage(
            applicationContext
        )

        val introPages = listOf(
            R.drawable.intro_1,
            R.drawable.intro_2,
            R.drawable.intro_3
        )

        val princessPages = listOf(
            R.drawable.princess_1,
            R.drawable.princess_2
        )

        val marcelinePages = listOf(
            R.drawable.marceline_1,
            R.drawable.marceline_2
        )

        val validItemIds = FirstLevelItems.items
            .map { it.id }
            .toSet()

        setContent {
            MaterialTheme {
                var currentScreen by rememberSaveable {
                    mutableStateOf("menu")
                }

                var comicPage by rememberSaveable {
                    mutableStateOf(0)
                }
                var princessComicPage by rememberSaveable {
                    mutableStateOf(0)
                }

                var marcelineComicPage by rememberSaveable {
                    mutableStateOf(0)
                }

                var foundItemIds by remember {
                    mutableStateOf(
                        progressStorage.getFoundItems()
                            .intersect(validItemIds)
                    )
                }

                val firstLevelCompleted =
                    foundItemIds.containsAll(validItemIds)

                BackHandler(
                    enabled = currentScreen != "menu"
                ) {
                    when (currentScreen) {
                        "comic" -> {
                            if (comicPage > 0) {
                                comicPage--
                            } else {
                                currentScreen = "menu"
                            }
                        }
                        "first_level" -> {
                            currentScreen = "menu"
                        }
                        "princess_comic" -> {
                            if (princessComicPage > 0) {
                                princessComicPage--
                            } else {
                                currentScreen = "menu"
                            }
                        }
                        "second_level" -> {
                            currentScreen = "menu"
                        }
                        "marceline_comic" -> {
                            if (marcelineComicPage > 0) {
                                marcelineComicPage--
                            } else {
                                currentScreen = "menu"
                            }
                        }
                        "third_level" -> {
                            currentScreen = "menu"
                        }
                    }
                }

                when (currentScreen) {
                    "menu" -> {
                        MainMenuScreen(
                            onNewGame = {
                                progressStorage.resetProgress()
                                foundItemIds = emptySet()
                                comicPage = 0
                                princessComicPage = 0
                                currentScreen = "comic"
                                marcelineComicPage = 0
                            },

                            onContinue = {
                                princessComicPage = 0
                                currentScreen = "princess_comic"
                            },

                            onSettings = {},

                            canContinue = firstLevelCompleted,

                            settingsEnabled = false
                        )
                    }

                    "comic" -> {
                        ComicScreen(
                            imageRes = introPages[comicPage],
                            buttonText = stringResource(
                                R.string.comic_next
                            ),
                            onNext = {
                                if (comicPage < introPages.lastIndex) {
                                    comicPage++
                                } else {
                                    currentScreen = "first_level"
                                }
                            }
                        )
                    }

                    "first_level" -> {
                        FirstLevelScreen(
                            foundItemIds = foundItemIds,

                            onItemFound = { itemId ->
                                if (
                                    itemId in validItemIds &&
                                    itemId !in foundItemIds
                                ) {
                                    val updatedItems =
                                        foundItemIds + itemId

                                    foundItemIds = updatedItems

                                    progressStorage.saveFirstLevelProgress(
                                        foundItemIds = updatedItems,
                                        completed = updatedItems
                                            .containsAll(validItemIds)
                                    )
                                }
                            },

                            onBackToMenu = {
                                currentScreen = "menu"
                            },

                            onContinue = {
                                princessComicPage = 0
                                currentScreen = "princess_comic"
                            }
                        )
                    }

                    "princess_comic" -> {
                        ComicScreen(
                            imageRes = princessPages[princessComicPage],

                            backgroundRes = R.drawable.level2_background,

                            titleRes = R.string.princess_comic_title,

                            buttonText = stringResource(
                                R.string.comic_next
                            ),

                            onNext = {
                                if (princessComicPage < princessPages.lastIndex) {
                                    princessComicPage++
                                } else {
                                    currentScreen = "second_level"
                                }
                            }
                        )
                    }
                    "second_level" -> {
                        SecondLevelScreen(
                            onBackToMenu = {
                                currentScreen = "menu"
                            },
                            onContinue = {
                                marcelineComicPage = 0
                                currentScreen = "marceline_comic"
                            }
                        )
                    }
                    "marceline_comic" -> {
                        ComicScreen(
                            imageRes = marcelinePages[marcelineComicPage],

                            backgroundRes = R.drawable.level3_background,

                            titleRes = R.string.marceline_comic_title,

                            buttonText = stringResource(
                                R.string.comic_next
                            ),

                            onNext = {
                                if (marcelineComicPage < marcelinePages.lastIndex) {
                                    marcelineComicPage++
                                } else {
                                    currentScreen = "third_level"
                                }
                            }
                        )
                    }
                    "third_level" -> {
                        ThirdLevelScreen(
                            onBackToMenu = {
                                currentScreen = "menu"
                            }
                        )
                    }
                }
            }
        }
    }
}