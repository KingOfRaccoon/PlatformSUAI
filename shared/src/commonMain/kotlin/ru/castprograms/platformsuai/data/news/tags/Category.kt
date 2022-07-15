package ru.castprograms.platformsuai.data.news.tags

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val CategoryId: Int,
    val Name: String,
    val Ord: Int,
    val Title: String
)