package ru.castprograms.platformsuai.android.ui.filter

data class HeaderFilterItem(val position: Int, val header: String): Section {
    override fun type() = Section.HEADER
    override fun sectionPosition() = position
}