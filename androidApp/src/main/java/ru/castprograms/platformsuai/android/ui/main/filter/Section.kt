package ru.castprograms.platformsuai.android.ui.main.filter

interface Section {
    fun type(): Int
    fun sectionPosition(): Int

    companion object {
        const val HEADER = 0
        const val ITEM = 1
    }
}