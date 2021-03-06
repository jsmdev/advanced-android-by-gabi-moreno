package com.keepcoding.instagramparapobres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keepcoding.instagramparapobres.databinding.GalleryItemBinding
import com.keepcoding.instagramparapobres.detail.DetailActivity
import com.keepcoding.instagramparapobres.gallery.Image

class GalleryRecyclerAdapter : RecyclerView.Adapter<GalleryViewHolder>() {

    var imageList: List<Image> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder =
        GalleryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { GalleryViewHolder(this) }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}

data class GalleryViewHolder(val binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(image: Image) {
        with(binding) {
            titleTextView.text = image.title ?: "No title"
            authorTextView.text = image.author ?: "Unknown"
            imageView.setImageBitmap(null)
            authorAvatarImageView.setImageBitmap(null)
            val urls = image.urls
            if (urls.size > 1) {
                btn.visibility = View.VISIBLE
                btn.setOnClickListener {
                    val context = btn.context
                    DetailActivity.start(
                        context,
                        urls
                    )
                }
            }
            Glide.with(root)
                .load(urls[0])
                .into(imageView)
            Glide.with(root)
                .load(image.authorAvatar)
                .also {
                    it.circleCrop()
                }
                .into(authorAvatarImageView)
        }
    }
}