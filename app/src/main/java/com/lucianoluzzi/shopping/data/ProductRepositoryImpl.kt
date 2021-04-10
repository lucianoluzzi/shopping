package com.lucianoluzzi.shopping.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import com.lucianoluzzi.shopping.domain.model.Product
import com.lucianoluzzi.shopping.domain.model.ProductEntry
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val apolloClient: ApolloClient
) : ProductRepository {

    override fun getProducts(
        pageSize: Int,
        searchText: String?
    ): Flow<PagingData<ProductEntry>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize
            )
        ) {
            ProductsPagingDataSource(
                pageSize = pageSize,
                apolloClient = apolloClient
            )
        }.flow
    }

    override fun getProduct(productId: String): Flow<Result<Product>> {
        TODO("Not yet implemented")
    }
}