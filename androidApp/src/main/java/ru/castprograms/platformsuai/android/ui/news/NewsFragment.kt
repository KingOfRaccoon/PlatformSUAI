package ru.castprograms.platformsuai.android.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.FragmentNewsBinding
import ru.castprograms.platformsuai.android.ui.main.SmallNewsAdapter
import ru.castprograms.platformsuai.util.Resource
import ru.castprograms.platformsuai.viewModels.MainViewModel

class NewsFragment: Fragment(R.layout.fragment_news) {
    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var binding: FragmentNewsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        loadData()

    }
    private fun loadData() {
        loadBannersAndNews()
    }
    private fun loadBannersAndNews() {
        MainScope().launch(Dispatchers.Main) {
            mainViewModel.newsFlow.collectLatest {
                if (it is Resource.Success) {
                    requireActivity().runOnUiThread {
                        binding.recyclerNew.adapter =
                            SmallNewsAdapter(it.data?.Pubs?.items?.take(5) ?: listOf())
                    }
                } else {
                    println(it.message)
                }
            }
        }
    }

}