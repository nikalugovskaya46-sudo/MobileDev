package com.example.mobiledevelopment.laba2.ui.localization

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.res.stringResource
import com.example.mobiledevelopment.R

val LocalEnglish = compositionLocalOf { false }

private val englishStrings = mapOf(
    R.string.menu_title to R.string.menu_title_en,
    R.string.menu_new_game to R.string.menu_new_game_en,
    R.string.menu_continue to R.string.menu_continue_en,
    R.string.menu_settings to R.string.menu_settings_en,
    R.string.settings_back to R.string.settings_back_en,

    R.string.settings_language to R.string.settings_language_en,
    R.string.settings_language_code to R.string.settings_language_code_en,
    R.string.settings_music to R.string.settings_music_en,
    R.string.settings_music_on to R.string.settings_music_on_en,
    R.string.settings_music_off to R.string.settings_music_off_en,

    R.string.comic_morning to R.string.comic_morning_en,
    R.string.comic_description to R.string.comic_description_en,
    R.string.comic_next to R.string.comic_next_en,
    R.string.princess_comic_title to R.string.princess_comic_title_en,
    R.string.marceline_comic_title to R.string.marceline_comic_title_en,
    R.string.lumpy_comic_title to R.string.lumpy_comic_title_en,

    R.string.level1_title to R.string.level1_title_en,
    R.string.level1_instruction to R.string.level1_instruction_en,
    R.string.item_found to R.string.item_found_en,
    R.string.item_not_found to R.string.item_not_found_en,
    R.string.level1_success_title to R.string.level1_success_title_en,
    R.string.level1_home to R.string.level1_home_en,

    R.string.level2_title to R.string.level2_title_en,
    R.string.level2_check to R.string.level2_check_en,
    R.string.level2_instruction to R.string.level2_instruction_en,


    R.string.level3_title to R.string.level3_title_en,
    R.string.level3_play to R.string.level3_play_en,

    R.string.level4_title to R.string.level4_title_en,
    R.string.level4_spin to R.string.level4_spin_en,
    R.string.level4_open to R.string.level4_open_en,
    R.string.level4_instruction to R.string.level4_instruction_en,

    R.string.ending_title to R.string.ending_title_en,
    R.string.ending_finish to R.string.ending_finish_en,
    R.string.ending_replay to R.string.ending_replay_en,
    R.string.ending_home to R.string.ending_home_en

)

@Composable
fun localizedStringResource(
    @StringRes id: Int,
    vararg formatArgs: Any
): String {
    val selectedId = if (LocalEnglish.current) {
        englishStrings[id] ?: id
    } else {
        id
    }

    return if (formatArgs.isEmpty()) {
        stringResource(selectedId)
    } else {
        stringResource(selectedId, *formatArgs)
    }
}