package com.lucianoluzzi.shopping.domain

import androidx.paging.PagingData
import com.lucianoluzzi.shopping.data.ProductRepository
import com.lucianoluzzi.shopping.domain.model.ProductEntry
import kotlinx.coroutines.flow.Flow

class GetFeedUseCaseImpl(
    private val repository: ProductRepository
) : GetFeedUseCase {

    override fun getFeed(): Flow<PagingData<ProductEntry>> = repository.getProducts()
}