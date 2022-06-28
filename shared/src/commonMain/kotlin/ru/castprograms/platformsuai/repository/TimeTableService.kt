package ru.castprograms.platformsuai.repository

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.castprograms.calendarkmmsuai.data.Lesson
import ru.castprograms.calendarkmmsuai.data.Semester
import ru.castprograms.calendarkmmsuai.util.Resource
import ru.castprograms.calendarkmmsuai.data.Group
import ru.castprograms.platformsuai.data.news.NewsData

class TimeTableService {
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }
    private val baseUrlRasp = "https://api.guap.ru/rasp/custom"
    private val baseUrlNews = "https://news.guap.ru/api/get-node-content"

    suspend fun getNews(nodeName: String): Resource<NewsData> {
        return try {
            Resource.Success(
                Json.decodeFromString(
                    httpClient.get("$baseUrlNews?node=$nodeName").body()
                )
            )
        } catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getSemInfo(): Resource<Semester> {
        return try {
            Resource.Success(
                Json.decodeFromString(
                    httpClient.get("$baseUrlRasp/get-sem-info").body()
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getGroups(): Resource<List<Group>> {
        return try {
            Resource.Success(
                Json.decodeFromString(
                    httpClient.get("$baseUrlRasp/get-sem_groups").body()
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getTimeTableGroup(numberGroup: String): Resource<Map<Int, List<Lesson>>> {
        return try {
            Resource.Success(
                (Json.decodeFromString(
                    httpClient.get("$baseUrlRasp/get-sem-rasp/group$numberGroup").body()
                ) as List<Lesson>).sortedBy { it.less }.groupBy { it.week * 10 + it.day }
            )
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}