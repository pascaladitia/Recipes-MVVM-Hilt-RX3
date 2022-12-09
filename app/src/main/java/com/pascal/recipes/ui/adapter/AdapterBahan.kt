//package com.pascal.recipes.ui.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.pascal.recipes.databinding.ItemBahanBinding
//import com.pascal.recipes.databinding.ItemTitleBahanBinding
//import com.pascal.recipes.data.model.IngredientsItem
//import com.pascal.recipes.data.model.ItemsItem
//
//class AdapterBahan(private val title: List<IngredientsItem?>?,
//                   private val bahan: List<ItemsItem?>?
//) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//
//        return if (viewType == titleHolder.VIEW_TYPE) {
//            val binding = ItemTitleBahanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            titleHolder(binding)
//        } else {
//            val binding = ItemBahanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            bahanHolder(binding)
//        }
//    }
//
//    override fun getItemCount() = title?.size!! + bahan!!.size
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//        when (holder) {
//            is titleHolder -> title?.get(position)?.let { holder.bind(it) }
//            is bahanHolder -> bahan?.get(position)?.let { holder.bind(it) }
//            //is bahanHolder -> bahan?.get((position - title!!.size))?.let { holder.bind(it) }
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//
//        if (position < title!!.size) {
//            return titleHolder.VIEW_TYPE
//        }
//
//        val realPosition = (position - title.size)
//        if (realPosition < bahan!!.size) {
//            return bahanHolder.VIEW_TYPE
//        }
//
//        return -1
//    }
//
//    class titleHolder(val binding: ItemTitleBahanBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        companion object {
//            const val VIEW_TYPE = 83
//        }
//
//        fun bind(title: IngredientsItem) = with(itemView) {
//            binding.textView11.text = title.title
//        }
//    }
//
//    class bahanHolder(val binding: ItemBahanBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        companion object {
//            const val VIEW_TYPE = 84
//        }
//
//        fun bind(bahan: ItemsItem) = with(itemView) {
//            binding.nama.text = bahan.item
//            binding.jumlah.text = bahan.qty
//        }
//    }
//}