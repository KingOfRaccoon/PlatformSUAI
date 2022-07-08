package ru.castprograms.platformsuai.repository.timetable

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.castprograms.calendarkmmsuai.data.Lesson
import ru.castprograms.calendarkmmsuai.data.Semester
import ru.castprograms.platformsuai.util.Resource
import ru.castprograms.calendarkmmsuai.data.Group

class TimetableService {
    private val json = Json { coerceInputValues = true }
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json
        }
    }
    private val baseUrl = "https://api.guap.ru/rasp/custom"

    suspend fun getSemInfo(): Resource<Semester> {
        return try {
            Resource.Success(
                json.decodeFromString(
                    httpClient.get("$baseUrl/get-sem-info").body()
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getGroups(): Resource<List<Group>> {
        return try {
            Resource.Success(
                json.decodeFromString(
                    httpClient.get("$baseUrl/get-sem_groups").body()
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getTimeTableGroup(numberGroup: String): Resource<Map<Int, List<Lesson>>> {
        return try {
            Resource.Success(
                (json.decodeFromString(
                    httpClient.get("$baseUrl/get-sem-rasp/group$numberGroup").body()
                ) as List<Lesson>).sortedBy { it.less }.groupBy { it.week * 10 + it.day }
            )
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}