package ru.castprograms.platformsuai.android.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.FragmentNewsBinding

class NewsFragment: Fragment(R.layout.fragment_news) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewsBinding.bind(view)
        binding.recyclerNew.adapter // сюды адаптер
    }
}