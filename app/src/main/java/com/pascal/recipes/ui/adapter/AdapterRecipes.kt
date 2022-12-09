package com.pascal.recipes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pascal.recipes.R
import com.pascal.recipes.data.model.MealsItem
import com.pascal.recipes.databinding.ItemRecipesBinding


class AdapterRecipes(
    val data: List<MealsItem?>?,
    val itemClick: OnClickListener
) : RecyclerView.Adapter<AdapterRecipes.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRecipes.ViewHolder {
        val binding = ItemRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterRecipes.ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bind(item!!)
    }

    override fun getItemCount(): Int = data!!.size

    inner class ViewHolder(val binding: ItemRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealsItem) {
            binding.title.text = item?.strMeal
            binding.desc.text = item?.strCategory

            Glide.with(binding.root)
                .load(item?.strMealThumb)
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
        fun detail(item: MealsItem)
        fun favorite(item: MealsItem)
    }
}