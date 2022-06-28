package ru.castprograms.platformsuai.data.news

import kotlinx.serialization.Serializable

@Serializable
data class Pubs(
    val HasNext: Boolean,
    val Items: List<Item>,
    val ItemsOnPage: Int
)