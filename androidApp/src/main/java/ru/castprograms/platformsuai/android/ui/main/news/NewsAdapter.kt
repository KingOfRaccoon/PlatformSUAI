package ru.castprograms.platformsuai.android.ui.main.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.castprograms.platformsuai.data.time.DataTime
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.ItemNewBinding
import ru.castprograms.platformsuai.android.ui.main.calendar.loadImage
import ru.castprograms.platformsuai.data.news.Item
import ru.castprograms.platformsuai.data.news.tags.TagFilter

class NewsAdapter(
    val scrollToPosition: (Int) -> Unit,
    val listener: TransferData
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    var filters = mutableListOf<TagFilter>()
        set(value) {
            val filterItems = items.filter {
                (it.ListTargets + it.ListTags +
                        it.ListNodes + it.ListCategories as List<TagFilter>)
                    .containsAll(value)
            }
            val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize() = filteredItems.size

                override fun getNewListSize() = filterItems.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return filteredItems[oldItemPosition].PubId == filterItems[newItemPosition].PubId
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return filteredItems[oldItemPosition] == filterItems[newItemPosition]
                }
            }, true)
            diff.dispatchUpdatesTo(this)
            filteredItems = filterItems
            scrollToPosition(0)
            field = value
        }

    var items = listOf<Item>()
        set(value) {
            val filterItems = value.filter {
                (it.ListTargets + it.ListTags +
                        it.ListNodes + it.ListCategories as List<TagFilter>)
                    .containsAll(filters)
            }
            val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize() = filteredItems.size

                override fun getNewListSize() = filterItems.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return filteredItems[oldItemPosition].PubId == filterItems[newItemPosition].PubId
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return filteredItems[oldItemPosition] == filterItems[newItemPosition]
                }
            }, true)
            diff.dispatchUpdatesTo(this)
            filteredItems = filterItems
            scrollToPosition(0)
            field = value
        }
    var filteredItems = listOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_new, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(filteredItems[position])
    }

    override fun getItemCount() = filteredItems.size

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemNewBinding.bind(view)

        fun bind(item: Item) {
            binding.imageNew.loadImage("https://media.guap.ru/${item.MediaCatalog}/_title.jpg")
            binding.textTitleNew.text = item.Title
            binding.textDayNew.text = DataTime(item.Date).getDate()
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.passData(item.MediaCatalog)
                }
            }
        }
    }
}