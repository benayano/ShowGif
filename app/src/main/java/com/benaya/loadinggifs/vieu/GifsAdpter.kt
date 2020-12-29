package com.benaya.loadinggifs.vieu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.benaya.loadinggifs.R
import com.benaya.loadinggifs.model.network.Data
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition

class GifsAdapter : RecyclerView.Adapter<GifViewHolder>() {

    var gifListImages: List<Data> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GifViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.gif_item_view, parent, false)
    )

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifItem = gifListImages[position]
        holder.bind(title = gifItem.title, imageURL = gifItem.images.downsized_medium.url)
    }

    override fun getItemCount() = gifListImages.size

    fun submitList(gifListImages: List<Data>) {
        this.gifListImages = gifListImages
        notifyDataSetChanged()
    }

}

class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.findViewById<ImageView>(R.id.gifImageView)
    private val titleView = itemView.findViewById<TextView>(R.id.gifTitle)

    fun bind(title: String, imageURL: String) {
        titleView.text = title

        Glide.with(itemView.context)
                .load(imageURL)
                .placeholder(R.drawable.loading)
                .thumbnail(Glide.with(itemView.context).load(R.drawable.loading))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
    }

}
