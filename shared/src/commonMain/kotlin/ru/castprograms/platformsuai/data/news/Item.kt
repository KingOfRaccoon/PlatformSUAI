package ru.castprograms.platformsuai.data.news

data class Item(
    val Abstract: String,
    val AuthorDate: String,
    val AuthorUserPtr: Int,
    val Content: Any,
    val Date: String,
    val Keywords: String,
    val ListCategories: List<ListCategory>,
    val ListNodes: List<Nodes>,
    val ListTags: List<Tags>,
    val ListTargets: List<Targets>,
    val MediaCatalog: Int,
    val Props: String,
    val PubId: Int,
    val Title: String,
    val TitleImageMediaUrl: String,
    val UrlExt: String
)