package ru.castprograms.platformsuai.android.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shuhart.stickyheader.StickyAdapter
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.ItemFilterBinding
import ru.castprograms.platformsuai.android.databinding.ItemFilterHeaderBinding
import ru.castprograms.platformsuai.data.news.tags.TagFilter
import ru.castprograms.platformsuai.data.news.tags.Node

class FiltersAdapter(
    var selectedItems: MutableList<TagFilter> = mutableListOf(),
    val addTagFilter: (TagFilter) -> Unit,
    val removeTagFilter: (TagFilter) -> Unit
) : StickyAdapter<FiltersAdapter.HeaderViewHolder, RecyclerView.ViewHolder>(), Filterable {

    var items = mutableListOf<Section>()
    var filteredList = listOf<TagFilter>()
        set(value) {
            var position = -1
            val newItems = mutableListOf<Section>()
            value.forEachIndexed { index, tagFilter ->
                if (index == 0 || tagFilter.category != value[index - 1].category) {
                    position++
                    newItems.add(HeaderFilterItem(position, tagFilter.category))
                }
                newItems.add(FilterItem(position, tagFilter))
            }
            DiffUtil.calculateDiff(getDiffFitter(newItems), true).dispatchUpdatesTo(this)
            items = newItems
            field = value
        }

    override fun getItemViewType(position: Int): Int {
        return items[position].type()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Section.HEADER) {
            HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_filter_header, parent, false)
            )
        } else FiltersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (items[position]) {
            is HeaderFilterItem -> {
                (holder as HeaderViewHolder).bind((items[position] as HeaderFilterItem).header)
            }
            is FilterItem -> {
                (holder as FiltersViewHolder).bind((items[position] as FilterItem).tagFilter)
            }
            else -> {}
        }
    }

    override fun getItemCount() = items.size

    private val diffCallback = object : DiffUtil.ItemCallback<TagFilter>() {
        override fun areItemsTheSame(oldItem: TagFilter, newItem: TagFilter) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: TagFilter, newItem: TagFilter) =
            oldItem == newItem
    }
    val differ = AsyncListDiffer(this, diffCallback).apply {
        addListListener { _, currentList ->
            filteredList = currentList
        }
    }

    inner class FiltersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemFilterBinding.bind(view)

        fun bind(filter: TagFilter) {
            binding.textChipGroupFilter.text =
                if (filter is Node && !filter.ShortTitle.isNullOrEmpty())
                    filter.ShortTitle
                else
                    filter.title

            binding.root.setOnClickListener {
                if (selectedItems.contains(filter))
                    removeTagFilter(filter)
                else
                    addTagFilter(filter)
            }
        }
    }

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemFilterHeaderBinding.bind(view)

        fun bind(filterTag: String) {
            binding.textTitleFilter.text = filterTag
        }
    }

    private fun getDiffFitter(newList: List<Section>): DiffUtil.Callback {
        return object : DiffUtil.Callback() {
            override fun getOldListSize() = items.size
            override fun getNewListSize() = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val newItem = newList[newItemPosition]
                val oldItem = items[oldItemPosition]
                return oldItem.type() == newItem.type() && oldItem.sectionPosition() == newItem.sectionPosition() ||
                        (newItem is FilterItem && oldItem is FilterItem && newItem.tagFilter == oldItem.tagFilter) ||
                        (newItem is HeaderFilterItem && oldItem is HeaderFilterItem && newItem.header == oldItem.header)
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val newItem = newList[newItemPosition]
                val oldItem = items[oldItemPosition]
                return (newItem is FilterItem && oldItem is FilterItem && newItem.tagFilter == oldItem.tagFilter) ||
                        (newItem is HeaderFilterItem && oldItem is HeaderFilterItem && newItem.header == oldItem.header)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?) = FilterResults().apply {
                values = differ.currentList.filter { it.title.contains(p0 ?: "", true) }
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                if (p1 != null && p1.values is List<*>) {
                    filteredList = p1.values as List<TagFilter>
                }
            }
        }
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        return items[itemPosition].sectionPosition()
    }

    override fun onBindHeaderViewHolder(holder: HeaderViewHolder, headerPosition: Int) {
        holder.bind((items.filter { it.type() == Section.HEADER }[headerPosition] as HeaderFilterItem).header)
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup): HeaderViewHolder {
        return HeaderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filter_header, parent, false)
        )
    }
}