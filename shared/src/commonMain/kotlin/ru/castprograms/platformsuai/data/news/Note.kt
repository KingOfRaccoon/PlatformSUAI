package ru.castprograms.platformsuai.data.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.castprograms.platformsuai.data.news.tags.Node
import ru.castprograms.platformsuai.data.news.tags.Target

@Serializable
data class Note(
    val AuthorDate: String,
    val AuthorUserPtr: Int,
    val BannerMediaUrl: String,
    val Content: String?,
    val DateBegin: String?,
    val DateEnd: String?,
    val DateOff: String,
    val DateOn: String,
    val IsBannerOnly: Boolean,
    val IsCalendar: Boolean,
    val IsWarning: Boolean,
    val Keywords: String,
    @SerialName("ListNodes")
    val listNodes: List<Node>?,
    @SerialName("ListTargets")
    val listTargets: List<Target>?,
    val MediaCatalog: Int,
    val MessageId: Int,
    val Notice: String,
    val Place: String,
    val Props: String,
    val Text: String,
    val TitleImageMediaUrl: String,
    val UrlExt: String,
    val Weight: Int,
    val WeightBanner: Int
)