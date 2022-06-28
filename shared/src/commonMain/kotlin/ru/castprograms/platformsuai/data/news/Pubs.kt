package ru.castprograms.platformsuai.data.news

data class Pubs(
    val HasNext: Boolean,
    val Items: List<Item>,
    val ItemsOnPage: Int
)