//package com.pascal.recipes.ui.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.Priority
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.bumptech.glide.request.RequestOptions
//import com.pascal.recipes.R
//import com.pascal.recipes.databinding.ItemStepBinding
//import com.pascal.recipes.data.model.StepsItem
//
//class AdapterStep(
//    val data: List<StepsItem?>?
//    ) : RecyclerView.Adapter<AdapterStep.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterStep.ViewHolder {
//        val binding = ItemStepBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: AdapterStep.ViewHolder, position: Int) {
//        val item = data?.get(position)
//        holder.bind(item!!)
//    }
//
//    override fun getItemCount(): Int = data!!.size
//
//    inner class ViewHolder(val binding: ItemStepBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: StepsItem) {
//            binding.num.text = "Steps ${item.num}"
//            binding.step.text = item?.step
//
//            Glide.with(binding.root)
//                .load(item?.images)
//                .apply(
//                    RequestOptions()
//                        .override(1000, 1000)
//                        .placeholder(R.drawable.progress_animation)
//                        .error(R.drawable.logo)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .priority(Priority.HIGH)
//                )
//                .into(binding.imageView5)
//        }
//    }
//}