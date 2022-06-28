package ru.castprograms.platformsuai.data.news

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val AuthorDate: String,
    val AuthorUserPtr: Int,
    val BannerMediaUrl: String,
    val Content: String?,
    val DateBegin: String,
    val DateEnd: String,
    val DateOff: String,
    val DateOn: String,
    val IsBannerOnly: Boolean,
    val IsCalendar: Boolean,
    val IsWarning: Boolean,
    val Keywords: String,
    val ListNodes: List<Nodes>?,
    val ListTargets: List<Targets>?,
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