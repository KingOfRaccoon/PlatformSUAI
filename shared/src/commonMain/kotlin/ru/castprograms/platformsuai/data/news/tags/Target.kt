package ru.castprograms.platformsuai.data.news.tags

import kotlinx.serialization.Serializable

@Serializable
data class Target(
    val Name: String,
    val Ord: Int,
    val TargetId: Int,
    val Title: String
)