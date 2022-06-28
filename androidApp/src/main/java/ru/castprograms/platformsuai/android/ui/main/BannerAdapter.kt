package ru.castprograms.platformsuai.android.ui.main

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moeidbannerlibrary.banner.BannerLayout
import ru.castprograms.platformsuai.android.R

class BannerAdapter(private val context: Context, private val urlList: MutableList<String>): RecyclerView.Adapter<BannerAdapter.MzViewHolder>(){
    private var onBannerItemClickListener: BannerLayout.OnBannerItemClickListener? = null

    fun setOnBannerItemClickListener(onBannerItemClickListener: BannerLayout.OnBannerItemClickListener) {
        this.onBannerItemClickListener = onBannerItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MzViewHolder {
        return MzViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MzViewHolder, position: Int) {
        if (urlList.isEmpty()) return
        val P = position % urlList.size
        val url = urlList[P]
        val img = holder.imageView
        img.setImageURI(Uri.parse(url))
        img.setOnClickListener {
            if (onBannerItemClickListener != null) {
                onBannerItemClickListener!!.onItemClick(P)
            }
        }
    }

    override fun getItemCount() = urlList.size


    class MzViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView

        init {
            imageView = itemView.findViewById<View>(R.id.image) as ImageView
        }
    }


}