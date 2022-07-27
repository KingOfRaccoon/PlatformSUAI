package ru.castprograms.platformsuai.viewModels

import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.postValue
import dev.icerock.moko.mvvm.livedata.readOnly
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.castprograms.platformsuai.data.news.NewsData
import ru.castprograms.platformsuai.data.news.tags.*
import ru.castprograms.platformsuai.dispatchers.ioDispatcher
import ru.castprograms.platformsuai.repository.news.NewsRepository
import ru.castprograms.platformsuai.util.Resource

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private val _newsFlow = MutableSharedFlow<Resource<NewsData>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    ).apply {
        viewModelScope.launch(ioDispatcher) {
            emit(Resource.Loading())
        }
    }
    val newsFlow = _newsFlow.asSharedFlow()


    private val _selectedTagFiltersLiveData = MutableLiveData(mutableListOf<TagFilter>())
    val selectedTagFiltersLiveData = _selectedTagFiltersLiveData.readOnly()

    fun addSelectedTagFilter(tagFilter: TagFilter) {
        _selectedTagFiltersLiveData.postValue(
            _selectedTagFiltersLiveData.value.apply { add(0, tagFilter) }
        )
    }

    fun removeSelectedTagFilter(tagFilter: TagFilter) {
        _selectedTagFiltersLiveData.postValue(
            _selectedTagFiltersLiveData.value.apply { remove(tagFilter) }
        )
    }

    private val _filtersFlow = MutableSharedFlow<List<Resource<out Pair<String, List<TagFilter>>>>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val filtersFlow = _filtersFlow.asSharedFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(ioDispatcher) {
            _filtersFlow.emit(
                listOf(
                    newsRepository.getCategories(),
                    newsRepository.getTargets(),
                    newsRepository.getNodes(),
                    newsRepository.getTags()
                )
            )
            _newsFlow.emit(newsRepository.getNews("1"))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}