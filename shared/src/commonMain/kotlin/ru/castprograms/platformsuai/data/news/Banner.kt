package ru.castprograms.platformsuai.data.news

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Banner(
    @SerialName("AuthorDate")
    val authorDate: String,
    @SerialName("AuthorUserPtr")
    val authorUserPtr: Int,
    @SerialName("BannerMediaUrl")
    val bannerMediaUrl: String,
    @SerialName("Content")
    val content: String?,
    @SerialName("DateBegin")
    val dateBegin: String?,
    @SerialName("DateEnd")
    val dateEnd: String?,
    @SerialName("DateOff")
    val dateOff: String,
    @SerialName("DateOn")
    val dateOn: String,
    @SerialName("IsBannerOnly")
    val isBannerOnly: Boolean,
    @SerialName("IsCalendar")
    val isCalendar: Boolean,
    @SerialName("IsWarning")
    val isWarning: Boolean,
    @SerialName("Keywords")
    val keywords: String,
    @SerialName("ListNodes")
    val listNodes: List<Nodes>?,
    @SerialName("ListTargets")
    val listTargets: List<Targets>?,
    @SerialName("MediaCatalog")
    val mediaCatalog: Int,
    @SerialName("MessageId")
    val messageId: Int,
    @SerialName("Notice")
    val notice: String,
    @SerialName("Place")
    val place: String,
    @SerialName("Props")
    val props: String,
    @SerialName("Text")
    val text: String,
    @SerialName("TitleImageMediaUrl")
    val titleImageMediaUrl: String,
    @SerialName("UrlExt")
    val urlExt: String,
    @SerialName("Weight")
    val weight: Int,
    @SerialName("WeightBanner")
    val weightBanner: Int
)