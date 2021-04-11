package com.lucianoluzzi.shopping.domain

import com.lucianoluzzi.shopping.data.Response
import com.lucianoluzzi.shopping.domain.model.Product

interface GetProductUseCase {
    suspend fun getProduct(productId: String): Response<Product>
}