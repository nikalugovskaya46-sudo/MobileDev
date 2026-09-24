package com.example.mobiledevelopment.laba1

class TextAnalyzer {

    fun findMostPopularWords(text: String): String {

        val words = text
            .lowercase()
            .split(Regex("[^а-яёa-z]+"))
            .filter { it.isNotEmpty() }

        val groups = words.groupBy { it.first() }

        val maxCount = groups.values
            .maxOfOrNull { it.size }
            ?: return "Слова не найдены"

        return groups
            .filter { it.value.size == maxCount }
            .entries
            .joinToString("\n") { (letter, words) ->
                "${letter.uppercaseChar()} — ${words.joinToString(", ")}"
            }
    }
}