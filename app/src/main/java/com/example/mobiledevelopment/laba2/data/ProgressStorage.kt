package com.example.mobiledevelopment.laba2.data

import android.content.Context

class ProgressStorage(context: Context) {

    private val preferences =
        context.applicationContext.getSharedPreferences(
            "quest_progress",
            Context.MODE_PRIVATE
        )

    fun getFoundItems(): Set<String> {
        return preferences.getStringSet(
            "first_level_found_items",
            emptySet()
        )?.toSet() ?: emptySet()
    }

    fun isFirstLevelCompleted(): Boolean {
        return preferences.getBoolean(
            "first_level_completed",
            false
        )
    }

    fun saveFirstLevelProgress(
        foundItemIds: Set<String>,
        completed: Boolean
    ) {
        preferences.edit()
            .putStringSet(
                "first_level_found_items",
                foundItemIds.toSet()
            )
            .putBoolean(
                "first_level_completed",
                completed
            )
            .apply()
    }

    fun resetProgress() {
        preferences.edit()
            .remove("first_level_found_items")
            .remove("first_level_completed")
            .apply()
    }
}