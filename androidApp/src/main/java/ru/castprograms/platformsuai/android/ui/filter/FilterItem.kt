package ru.castprograms.platformsuai.android.ui.filter

import ru.castprograms.platformsuai.data.news.tags.TagFilter

data class FilterItem(val position: Int, val tagFilter: TagFilter): Section {
    override fun type() = Section.ITEM

    override fun sectionPosition() = position
}