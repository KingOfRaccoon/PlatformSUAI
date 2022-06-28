package ru.castprograms.platformsuai.data.news

import kotlinx.serialization.Serializable

@Serializable
data class ListCategory(
    val CategoryId: Int,
    val Name: String,
    val Ord: Int,
    val Title: String
)