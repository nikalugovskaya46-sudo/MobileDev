package com.example.mobiledevelopment.laba2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
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
import androidx.activity.enableEdgeToEdge

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
                    }
                }

                when (currentScreen) {

                    "menu" -> {
                        MainMenuScreen(
                            onNewGame = {
                                progressStorage.resetProgress()
                                foundItemIds = emptySet()
                                comicPage = 0
                                currentScreen = "comic"
                            },

                            onContinue = {
                                currentScreen = "first_level"
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

                                    progressStorage
                                        .saveFirstLevelProgress(
                                            foundItemIds = updatedItems,
                                            completed = updatedItems
                                                .containsAll(validItemIds)
                                        )
                                }
                            },

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