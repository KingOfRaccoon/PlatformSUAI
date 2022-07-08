package ru.castprograms.platformsuai.repository.news

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.castprograms.platformsuai.data.news.NewsData
import ru.castprograms.platformsuai.util.Resource

class NewsService {
    private val json = Json { coerceInputValues = true }
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json
        }
    }
    private val baseUrl = "https://news.guap.ru/api/get-node-content"

    suspend fun getNews(nodeName: String): Resource<NewsData> {
        return try {
            Resource.Success(
                json.decodeFromString(
                    httpClient.get("$baseUrl?node=$nodeName").body()
                )
            )
        } catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }
}