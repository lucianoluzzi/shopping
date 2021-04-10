package com.lucianoluzzi.shopping.domain.model

data class ProductEntry(
    val id: String,
    val image: String,
    val brand: String,
    val price: Float,
    val currency: String
)
