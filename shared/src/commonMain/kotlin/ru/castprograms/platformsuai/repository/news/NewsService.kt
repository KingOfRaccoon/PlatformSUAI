package ru.castprograms.platformsuai.repository.news

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.castprograms.platformsuai.data.news.NewsData
import ru.castprograms.platformsuai.data.news.tags.Tag
import ru.castprograms.platformsuai.util.Resource

class NewsService {
    private val json = Json { coerceInputValues = true }
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json
        }
    }
    private val baseUrl = "https://news.guap.ru/api/"
    private val getNodeContent = "get-node-content"
    private val getActiveTags = "get-active-tags"
    private val getCategories = "get-categories"
    private val getTargets = "get-targets"
    private val getActiveNodes = "get-active-nodes"

    suspend fun getNews(nodeName: String): Resource<NewsData> {
        return try {
            Resource.Success(
                json.decodeFromString(
                    httpClient.get("$baseUrl$getNodeContent?node=$nodeName").body()
                )
            )
        } catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getActiveTags(): Resource<List<Tag>>{
        return try {
            Resource.Success(
                json.decodeFromString(
                    httpClient.get("$baseUrl$getActiveTags").body()
                )
            )
        } catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }
}