package ru.castprograms.platformsuai.data.news.tags

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val PubCount: Int = 0,
    val TagId: Int,
    @SerialName("Name")
    override val name: String,
    @SerialName("Title")
    override val title: String
): TagFilter()