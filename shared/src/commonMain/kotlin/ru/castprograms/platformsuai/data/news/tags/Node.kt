package ru.castprograms.platformsuai.data.news.tags

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Node(
    val PubCount: Int = 0,
    val NodeId: Int,
    val Ord: Int,
    val ParentNodePtr: Int?,
    val ShortTitle: String?,
    @SerialName("Name")
    override val name: String,
    @SerialName("Title")
    override val title: String
): TagFilter()