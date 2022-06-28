package ru.castprograms.platformsuai.data.news

import kotlinx.serialization.Serializable

@Serializable
data class Tags(
    val Name: String,
    val TagId: Int,
    val Title: String
)