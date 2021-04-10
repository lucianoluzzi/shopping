package com.lucianoluzzi.shopping.domain

import androidx.paging.PagingData
import com.lucianoluzzi.shopping.domain.model.ProductEntry
import kotlinx.coroutines.flow.Flow

interface GetFeedUseCase {
    fun getFeed(): Flow<PagingData<ProductEntry>>
}