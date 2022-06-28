package ru.castprograms.platformsuai.data.news

data class NewsData(
    val Banners: List<Banner>,
    val Events: List<Event>,
    val NodeId: Int,
    val Notes: List<Note>,
    val Pubs: Pubs,
    val TargetId: Int
)