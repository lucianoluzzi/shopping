package com.lucianoluzzi.shopping.data

import SingleProductQuery
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.lucianoluzzi.shopping.domain.model.Product
import com.lucianoluzzi.shopping.domain.model.ProductEntry
import com.lucianoluzzi.shopping.domain.model.mapper.ProductMapper
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

class ProductRepositoryImpl(
    private val apolloClient: ApolloClient
) : ProductRepository {

    override fun getProducts(
        pageSize: Int
    ): Flow<PagingData<ProductEntry>> {
        val pagingConfig = PagingConfig(
            pageSize = pageSize
        )
        val pager = Pager(config = pagingConfig) {
            ProductsPagingDataSource(
                pageSize = pageSize,
                apolloClient = apolloClient
            )
        }
        return pager.flow
    }

    @Throws(ApolloException::class)
    override suspend fun getProduct(productId: String): Response<Product>? {
        val getProductQuery = SingleProductQuery(productId)
        val resultData = apolloClient.query(getProductQuery).await().data
        return resultData?.product?.let {
            val product = ProductMapper().map(it)
            return Response.Success(product)
        }
    }
}