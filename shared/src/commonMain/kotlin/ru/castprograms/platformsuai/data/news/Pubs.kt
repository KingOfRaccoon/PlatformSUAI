package ru.castprograms.platformsuai.data.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pubs(
    val HasNext: Boolean,
    @SerialName("Items")
    val items: List<Item>,
    val ItemsOnPage: Int
)