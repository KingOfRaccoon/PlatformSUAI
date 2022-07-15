package ru.castprograms.platformsuai.data.news.tags

import kotlinx.serialization.Serializable

@Serializable
data class Node(
    val Name: String,
    val NodeId: Int,
    val Ord: Int,
    val ParentNodePtr: Int?,
    val ShortTitle: String,
    val Title: String
)