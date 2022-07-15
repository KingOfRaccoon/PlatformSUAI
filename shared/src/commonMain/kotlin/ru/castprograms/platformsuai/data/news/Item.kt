package ru.castprograms.platformsuai.data.news

import kotlinx.serialization.Serializable
import ru.castprograms.platformsuai.data.news.tags.Category
import ru.castprograms.platformsuai.data.news.tags.Node
import ru.castprograms.platformsuai.data.news.tags.Tag
import ru.castprograms.platformsuai.data.news.tags.Target

@Serializable
data class Item(
    val Abstract: String,
    val AuthorDate: String,
    val AuthorUserPtr: Int,
    val Content: String?,
    val Date: String,
    val Keywords: String,
    val ListCategories: List<Category>,
    val ListNodes: List<Node>,
    val ListTags: List<Tag>,
    val ListTargets: List<Target>,
    val MediaCatalog: Int,
    val Props: String,
    val PubId: Int,
    val Title: String,
    val TitleImageMediaUrl: String,
    val UrlExt: String
)