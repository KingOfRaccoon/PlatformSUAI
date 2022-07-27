package ru.castprograms.platformsuai.data.news.tags

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("CategoryId")
    val categoryId: Int,
    @SerialName("Ord")
    val ord: Int,
    @SerialName("Name")
    override val name: String,
    @SerialName("Title")
    override val title: String
): TagFilter()