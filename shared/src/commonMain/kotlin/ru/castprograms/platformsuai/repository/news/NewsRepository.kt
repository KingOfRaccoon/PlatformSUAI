package ru.castprograms.platformsuai.repository.news

class NewsRepository(private val newsService: NewsService) {

    suspend fun getNews(nodeName: String) = newsService.getNews(nodeName)
}