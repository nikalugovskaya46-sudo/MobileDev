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
import com.example.mobiledevelopment.laba2.ui.level4.FourthLevelScreen
import com.example.mobiledevelopment.laba2.ui.result.ResultScreen

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

        val lumpyPages = listOf(
            R.drawable.lumpy_1,
            R.drawable.lumpy_2
        )

        val endingPages = listOf(
            R.drawable.ending_1,
            R.drawable.ending_2,
            R.drawable.ending_3
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

                var lumpyComicPage by rememberSaveable {
                    mutableStateOf(0)
                }

                var foundItemIds by remember {
                    mutableStateOf(
                        progressStorage.getFoundItems()
                            .intersect(validItemIds)
                    )
                }

                var endingPage by rememberSaveable {
                    mutableStateOf(0)
                }

                var questCompleted by remember {
                    mutableStateOf(
                        progressStorage.isQuestCompleted()
                    )
                }

                val firstLevelCompleted =
                    foundItemIds.containsAll(validItemIds)

                val startNewGame: () -> Unit = {
                    progressStorage.resetProgress()

                    foundItemIds = emptySet()
                    questCompleted = false

                    comicPage = 0
                    princessComicPage = 0
                    marcelineComicPage = 0
                    lumpyComicPage = 0
                    endingPage = 0

                    currentScreen = "comic"
                }

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
                        "lumpy_comic" -> {
                            if (lumpyComicPage > 0) {
                                lumpyComicPage--
                            } else {
                                currentScreen = "menu"
                            }
                        }
                        "fourth_level" -> {
                            currentScreen = "menu"
                        }
                        "ending_comic" -> {
                            if (endingPage > 0) {
                                endingPage--
                            } else {
                                currentScreen = "menu"
                            }
                        }

                        "result" -> {
                            currentScreen = "menu"
                        }
                    }
                }

                when (currentScreen) {
                    "menu" -> {
                        MainMenuScreen(
                            onNewGame = startNewGame,

                            onContinue = {
                                if (questCompleted) {
                                    currentScreen = "result"
                                } else {
                                    princessComicPage = 0
                                    currentScreen = "princess_comic"
                                }
                            },

                            onSettings = {},

                            canContinue = firstLevelCompleted || questCompleted,

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
                            },
                            onContinue = {
                                lumpyComicPage = 0
                                currentScreen = "lumpy_comic"
                            }
                        )
                    }
                    "lumpy_comic" -> {
                        ComicScreen(
                            imageRes = lumpyPages[lumpyComicPage],

                            backgroundRes = R.drawable.level4_background,

                            titleRes = R.string.lumpy_comic_title,

                            buttonText = stringResource(
                                R.string.comic_next
                            ),

                            onNext = {
                                if (lumpyComicPage < lumpyPages.lastIndex) {
                                    lumpyComicPage++
                                } else {
                                    currentScreen = "fourth_level"
                                }
                            }
                        )
                    }
                    "fourth_level" -> {
                        FourthLevelScreen(
                            onBackToMenu = {
                                currentScreen = "menu"
                            },
                            onContinue = {
                                endingPage = 0
                                currentScreen = "ending_comic"
                            }
                        )
                    }
                    "ending_comic" -> {
                        ComicScreen(
                            imageRes = endingPages[endingPage],

                            backgroundRes = R.drawable.level4_background,

                            titleRes = R.string.ending_title,

                            buttonText = stringResource(
                                if (endingPage < endingPages.lastIndex) {
                                    R.string.comic_next
                                } else {
                                    R.string.ending_finish
                                }
                            ),

                            onNext = {
                                if (endingPage < endingPages.lastIndex) {
                                    endingPage++
                                } else {
                                    progressStorage.completeQuest()
                                    questCompleted = true
                                    currentScreen = "result"
                                }
                            }
                        )
                    }

                    "result" -> {
                        ResultScreen(
                            onMainMenu = {
                                currentScreen = "menu"
                            },
                            onRestart = startNewGame
                        )
                    }
                }
            }
        }
    }
}