package ru.castprograms.platformsuai.data.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsData(
    @SerialName("ItemsOnPage")
    val itemsOnPage: Int,
    @SerialName("Items")
    val items: List<Item>,
    @SerialName("HasNext")
    val hasNext: Boolean
)