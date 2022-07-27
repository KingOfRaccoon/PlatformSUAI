package ru.castprograms.platformsuai.data.news.tags

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Target(
    val Ord: Int,
    val TargetId: Int,
    @SerialName("Name")
    override val name: String,
    @SerialName("Title")
    override val title: String
): TagFilter()