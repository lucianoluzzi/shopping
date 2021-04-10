package com.lucianoluzzi.shopping.domain.model

data class Product(
    val id: String,
    val images: List<String>,
    val description: String,
    val price: Float,
    val currency: String,
    val seller: String
)
