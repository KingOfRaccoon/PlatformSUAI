package ru.castprograms.platformsuai.data.news

data class Nodes(
    val Name: String,
    val NodeId: Int,
    val Ord: Int,
    val ParentNodePtr: Int,
    val ShortTitle: String,
    val Title: String
)