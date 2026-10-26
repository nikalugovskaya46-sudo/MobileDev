package com.example.mobiledevelopment.laba2.data

import android.content.Context

class ProgressStorage(context: Context) {

    private val preferences =
        context.applicationContext.getSharedPreferences(
            "quest_progress",
            Context.MODE_PRIVATE
        )

    fun isFirstLevelCompleted(): Boolean {
        return preferences.getBoolean(
            "first_level_completed",
            false
        )
    }

    fun completeFirstLevel() {
        preferences.edit()
            .putBoolean("first_level_completed", true)
            .apply()
    }
}