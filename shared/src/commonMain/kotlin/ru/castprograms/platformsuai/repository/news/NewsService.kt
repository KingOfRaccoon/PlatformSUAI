package ru.castprograms.platformsuai.repository.news

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.castprograms.platformsuai.data.news.NewsData
import ru.castprograms.platformsuai.data.news.tags.Category
import ru.castprograms.platformsuai.data.news.tags.Node
import ru.castprograms.platformsuai.data.news.tags.Tag
import ru.castprograms.platformsuai.data.news.tags.Target
import ru.castprograms.platformsuai.util.Resource

class NewsService {
    private val json = Json { coerceInputValues = true }
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
    }
    private val baseUrl = "https://news.guap.ru/api/"
    private val getPubs = "get-pubs"
    private val tagActiveTags = "get-active-tags"
    private val tagCategories = "get-categories"
    private val tagTargets = "get-targets"
    private val tagActiveNodes = "get-active-nodes"
    private val itemsOnPage = 20

    suspend fun getNews(nodeName: String): Resource<NewsData> {
        return try {
            Resource.Success(
                json.decodeFromString(
                    httpClient.get("$baseUrl$getPubs?nodes=$nodeName&itemsOnPage=$itemsOnPage").body()
                )
            )
        } catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getActiveTags(): Resource<Pair<String, List<Tag>>>{
        return try {
            Resource.Success(
                "Жизнь вуза" to json.decodeFromString(
                    httpClient.get("$baseUrl$tagActiveTags").body()
                )
            )
        } catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getCategories(): Resource<Pair<String, List<Category>>>{
        return try {
            Resource.Success(
                "Глобальные категории" to json.decodeFromString(
                    httpClient.get("$baseUrl$tagCategories").body()
                )
            )
        } catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getTargets(): Resource<Pair<String, List<Target>>>{
        return try {
            Resource.Success(
                "Целевая аудитория" to json.decodeFromString(
                    httpClient.get("$baseUrl$tagTargets").body()
                )
            )
        } catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getActiveNodes(): Resource<Pair<String, List<Node>>>{
        return try {
            Resource.Success(
                "Жизнь в учебе" to json.decodeFromString(
                    httpClient.get("$baseUrl$tagActiveNodes").body()
                )
            )
        } catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }
}