package ru.castprograms.platformsuai.data.news

import kotlinx.serialization.Serializable

@Serializable
data class Targets(
    val Name: String,
    val Ord: Int,
    val TargetId: Int,
    val Title: String
)