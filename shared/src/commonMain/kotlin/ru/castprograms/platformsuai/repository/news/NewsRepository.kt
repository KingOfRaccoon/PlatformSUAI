package ru.castprograms.platformsuai.repository.news

class NewsRepository(private val newsService: NewsService) {

    suspend fun getNews(nodeName: String) = newsService.getNews(nodeName)

    suspend fun getCategories() = newsService.getCategories()
    suspend fun getTags() = newsService.getActiveTags()
    suspend fun getNodes() = newsService.getActiveNodes()
    suspend fun getTargets() = newsService.getTargets()
}