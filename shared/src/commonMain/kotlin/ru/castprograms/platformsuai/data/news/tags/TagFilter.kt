package ru.castprograms.platformsuai.data.news.tags

abstract class TagFilter {
    abstract val name: String
    abstract val title: String
    var category = ""

    override fun equals(other: Any?): Boolean {
        return if (other is TagFilter)
            name == other.name && title == other.title && category == other.category
        else
            false
    }
}