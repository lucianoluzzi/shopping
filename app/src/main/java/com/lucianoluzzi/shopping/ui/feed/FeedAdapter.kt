package com.lucianoluzzi.shopping.ui.feed

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lucianoluzzi.shopping.databinding.ItemProductEntryBinding
import com.lucianoluzzi.shopping.domain.model.ProductEntry
import com.lucianoluzzi.shopping.extensions.setWidth
import com.lucianoluzzi.shopping.extensions.toPx

class FeedAdapter : PagingDataAdapter<ProductEntry, FeedAdapter.ProductEntryViewHolder>(
    ProductEntryDiffUtil()
) {

    override fun onBindViewHolder(holder: ProductEntryViewHolder, position: Int) {
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels - 16.toPx()
        val columnWidth = screenWidth / 2
        holder.itemView.setWidth(columnWidth)
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductEntryViewHolder {
        val itemBinding = ItemProductEntryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductEntryViewHolder(itemBinding)
    }

    inner class ProductEntryViewHolder(private val binding: ItemProductEntryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productEntry: ProductEntry) = with(binding) {
            description.text = productEntry.brand
            price.text = "${productEntry.currency} ${productEntry.price}"
            image.load(productEntry.image)
        }
    }

    class ProductEntryDiffUtil : DiffUtil.ItemCallback<ProductEntry>() {
        override fun areItemsTheSame(oldItem: ProductEntry, newItem: ProductEntry): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ProductEntry, newItem: ProductEntry): Boolean =
            oldItem == newItem
    }
}
