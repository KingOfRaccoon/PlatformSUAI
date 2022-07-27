package ru.castprograms.platformsuai.android.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dev.icerock.moko.mvvm.livedata.Closeable
import dev.icerock.moko.mvvm.livedata.addCloseableObserver
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.FragmentNewsBinding
import ru.castprograms.platformsuai.android.ui.filter.FilterChipsAdapter
import ru.castprograms.platformsuai.android.ui.filter.FilterFragment
import ru.castprograms.platformsuai.util.Resource
import ru.castprograms.platformsuai.viewModels.NewsViewModel

class NewsFragment : Fragment(R.layout.fragment_news) {
    private val newsViewModel: NewsViewModel by viewModel()
    private val newsAdapter = NewsAdapter { binding.recyclerNew.scrollToPosition(it) }
    private val chipFilterAdapter =
        FilterChipsAdapter({ newsViewModel.removeSelectedTagFilter(it) }) {
            binding.recyclerSelectedFilters.scrollToPosition(it)
        }
    private lateinit var binding: FragmentNewsBinding
    private lateinit var observer: Closeable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        binding.recyclerNew.adapter = newsAdapter
        loadData()
        binding.buttonSort.setOnClickListener {
            FilterFragment().show(childFragmentManager, "tag")
        }
        binding.recyclerSelectedFilters.adapter = chipFilterAdapter

        observer = newsViewModel.selectedTagFiltersLiveData.addCloseableObserver { list ->
            chipFilterAdapter.tagFilters = list.toList()
            newsAdapter.filters = list
        }
    }

    private fun loadData() {
        loadNews()
    }

    private fun loadNews() {
        lifecycleScope.launch {
            newsViewModel.newsFlow.collectLatest {
                if (it is Resource.Success) {
                    newsAdapter.items = it.data?.items ?: listOf()
                } else {
                    println(it.message)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        observer.close()
    }
}