package com.lucianoluzzi.shopping.extensions

import android.view.View

fun View.setWidth(width: Int) {
    setWidthAndHeight(
        width = width,
        height = null
    )
}

fun View.setWidthAndHeight(width: Int? = null, height: Int? = null) {
    val lp = layoutParams ?: return

    width?.let {
        lp.width = it
    }
    height?.let {
        lp.height = it
    }

    layoutParams = lp
}