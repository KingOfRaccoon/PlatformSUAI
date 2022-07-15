package ru.castprograms.platformsuai.data.news.tags

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val Name: String,
    val PubCount: Int = 0,
    val TagId: Int,
    val Title: String
)