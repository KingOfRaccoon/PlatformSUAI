package ru.castprograms.platformsuai.data.news

data class Note(
    val AuthorDate: String,
    val AuthorUserPtr: Int,
    val BannerMediaUrl: String,
    val Content: Any,
    val DateBegin: Any,
    val DateEnd: Any,
    val DateOff: String,
    val DateOn: String,
    val IsBannerOnly: Boolean,
    val IsCalendar: Boolean,
    val IsWarning: Boolean,
    val Keywords: String,
    val ListNodes: Any,
    val ListTargets: Any,
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