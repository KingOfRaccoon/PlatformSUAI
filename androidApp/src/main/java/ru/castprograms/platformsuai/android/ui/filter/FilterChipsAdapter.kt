package ru.castprograms.platformsuai.android.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.ItemChipFilterBinding
import ru.castprograms.platformsuai.data.news.tags.Node
import ru.castprograms.platformsuai.data.news.tags.TagFilter

class FilterChipsAdapter(
    val removeTagFilter: (TagFilter) -> Unit,
    val scrollToPosition: (Int) -> Unit
) : RecyclerView.Adapter<FilterChipsAdapter.FilterChipsViewHolder>() {
    var tagFilters = listOf<TagFilter>()
    set(value) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback(){
            override fun getOldListSize() = field.size

            override fun getNewListSize() = value.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return field[oldItemPosition].name == value[newItemPosition].name
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return field[oldItemPosition] == value[newItemPosition]
            }
        }, true)
        diff.dispatchUpdatesTo(this)
        field = value
        scrollToPosition(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterChipsViewHolder {
        return FilterChipsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chip_filter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FilterChipsViewHolder, position: Int) {
        holder.bind(tagFilters[position])
    }

    override fun getItemCount() = tagFilters.size

    inner class FilterChipsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemChipFilterBinding.bind(view)

        fun bind(filter: TagFilter) {
            binding.root.text = if (filter is Node && !filter.ShortTitle.isNullOrEmpty())
                filter.ShortTitle
            else
                filter.title

            binding.root.setOnCloseIconClickListener {
                removeTagFilter(filter)
            }
        }
    }
}