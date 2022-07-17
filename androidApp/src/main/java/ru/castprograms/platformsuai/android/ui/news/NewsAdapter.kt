package ru.castprograms.platformsuai.android.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.castprograms.platformsuai.data.time.DataTime
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.ItemNewBinding
import ru.castprograms.platformsuai.android.ui.main.loadImage
import ru.castprograms.platformsuai.data.news.Item

class NewsAdapter(
    private val news: List<Item>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_new, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount() = news.size

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemNewBinding.bind(view)

        fun bind(item: Item) {
            binding.imageNew.loadImage("https://media.guap.ru/${item.MediaCatalog}/_title.jpg")
            binding.textTitleNew.text = item.Title
            binding.textDayNew.text = DataTime(item.Date).getDate()
        }
    }
}
