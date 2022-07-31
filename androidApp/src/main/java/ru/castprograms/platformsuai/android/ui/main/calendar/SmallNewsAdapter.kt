package ru.castprograms.platformsuai.android.ui.main.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.ItemNewSmallBinding
import ru.castprograms.platformsuai.data.news.Item

class SmallNewsAdapter(val items: List<Item>): RecyclerView.Adapter<SmallNewsAdapter.SmallNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallNewsViewHolder {
        return SmallNewsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_new_small, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SmallNewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class SmallNewsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemNewSmallBinding.bind(view)

        fun bind(item: Item){
            binding.imageNewInCard.loadImage("https://media.guap.ru/${item.MediaCatalog}/_title.jpg")
            binding.bodyNew.text = item.Title
        }
    }
}

// расширяющая функция для асинхронной загрузки фотографий
fun ImageView.loadImage(uri: String) {
    this.post {
        val myOptions = RequestOptions()
            .override(this.width, this.height)
//            .placeholder(R.drawable.attach)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide
            .with(this.context)
            .load(uri)
            .apply(myOptions)
            .into(this)
    }
}