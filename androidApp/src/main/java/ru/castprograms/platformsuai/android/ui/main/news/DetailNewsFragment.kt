package ru.castprograms.platformsuai.android.ui.main.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.FragmentDetailNewsBinding
import ru.castprograms.platformsuai.android.ui.main.calendar.loadImage

class DetailNewsFragment : Fragment(R.layout.fragment_detail_news) {
    private val args: DetailNewsFragmentArgs by navArgs()
    private lateinit var navController: NavController
    private lateinit var binding: FragmentDetailNewsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val newsId : Int = args.newsMediaCatalogId
        navController = view.findNavController()
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailNewsBinding.bind(view)
        loadData(newsId)
    }
    private fun loadData(newsId: Int) {
        binding.textTitleNew.text = "здесь будет основной текст новости"
        binding.textDayNew.text = "дата публикации"
        binding.imageDetail.loadImage("https://media.guap.ru/$newsId/_title.jpg")
    }
}