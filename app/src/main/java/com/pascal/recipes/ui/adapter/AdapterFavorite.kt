package com.pascal.recipes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pascal.recipes.R
import com.pascal.recipes.data.local.model.Favorite
import com.pascal.recipes.databinding.ItemRecipesBinding


class AdapterFavorite(
    val data: List<Favorite?>,
    val itemClick: OnClickListener
) : RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFavorite.ViewHolder {
        val binding = ItemRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterFavorite.ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bind(item!!)
    }

    override fun getItemCount(): Int = data!!.size

    inner class ViewHolder(val binding: ItemRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Favorite) {
            binding.title.text = item?.title
            binding.desc.text = item?.desc

            Glide.with(binding.root)
                .load(item?.url)
                .apply(
                    RequestOptions()
                        .override(1000, 1000)
                        .placeholder(R.drawable.progress_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)
                )
                .into(binding.imageView)

            binding.root.setOnClickListener {
                itemClick.detail(item)
            }
            binding.fav.setOnClickListener {
                itemClick.favorite(item)
            }
        }
    }

    interface OnClickListener {
        fun detail(item: Favorite)
        fun favorite(item: Favorite)
    }
}