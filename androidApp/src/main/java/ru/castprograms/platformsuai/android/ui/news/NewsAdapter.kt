package ru.castprograms.platformsuai.android.ui.news


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news.view.*
import ru.castprograms.platformsuai.android.R

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val dataSet = ArrayList<Item>()

    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: Item) {
            itemView.image_new_in_card.setImageResource(item.imageId)
            itemView.body_new.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    @SuppressLint("NotifyDataSetChanged")
    fun addItem (item: Item) {
        dataSet.add(item)
        notifyDataSetChanged()
    }
}
