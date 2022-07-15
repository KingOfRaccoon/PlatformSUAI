package ru.castprograms.platformsuai.android.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.ItemNewsBinding
import ru.castprograms.platformsuai.data.news.Item

class SmallNewsAdapter(val items: List<Item>): RecyclerView.Adapter<SmallNewsAdapter.SmallNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallNewsViewHolder {
        return SmallNewsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SmallNewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class SmallNewsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemNewsBinding.bind(view)

        fun bind(item: Item){
            binding.imageNewInCard.loadImage("https://media.guap.ru/${item.MediaCatalog}/_title.jpg")
            binding.bodyNew.text = item.Title
        }
    }
}

// расширяющая функция для асинхронной загрузки фотографий
fun ImageView.loadImage(uri: String) {
    this.post {
        Picasso.get()
            .load(uri)
            .resize(this.width, this.height)
            .centerCrop()
            .into(this)
    }
}