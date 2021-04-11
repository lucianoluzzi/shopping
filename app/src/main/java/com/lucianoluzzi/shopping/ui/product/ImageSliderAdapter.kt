package com.lucianoluzzi.shopping.ui.product

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImageSliderAdapter(
    parentFragment: Fragment,
    private val images: List<String>
) : FragmentStateAdapter(parentFragment) {

    override fun getItemCount() = images.size

    override fun createFragment(position: Int) = ItemImageFragment(images[position])
}