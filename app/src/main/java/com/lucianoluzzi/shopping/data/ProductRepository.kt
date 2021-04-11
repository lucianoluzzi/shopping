package com.lucianoluzzi.shopping.data

import androidx.paging.PagingData
import com.lucianoluzzi.shopping.domain.model.Product
import com.lucianoluzzi.shopping.domain.model.ProductEntry
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(
        pageSize: Int = 10
    ): Flow<PagingData<ProductEntry>>

    suspend fun getProduct(productId: String): Response<Product>?
}