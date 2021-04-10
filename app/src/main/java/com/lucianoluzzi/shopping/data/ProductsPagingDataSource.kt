package com.lucianoluzzi.shopping.data

import ProductFeedQuery
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.lucianoluzzi.shopping.domain.model.FeedRequest
import com.lucianoluzzi.shopping.domain.model.ProductEntry
import com.lucianoluzzi.shopping.domain.model.mapper.ProductEntryMapper

class ProductsPagingDataSource(
    private val pageSize: Int,
    private val apolloClient: ApolloClient
) : PagingSource<FeedRequest, ProductEntry>() {

    override fun getRefreshKey(state: PagingState<FeedRequest, ProductEntry>): FeedRequest? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<FeedRequest>): LoadResult<FeedRequest, ProductEntry> {
        return try {
            val feedRequest = params.key
            val query = ProductFeedQuery(
                pageSize = pageSize,
                pageToken = Input.optional(feedRequest?.pageToken)
            )

            val responseData = apolloClient.query(query).await().data
            LoadResult.Page(
                data = getResultData(responseData),
                prevKey = feedRequest,
                nextKey = getNextFeedRequest(responseData)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    private fun getResultData(responseData: ProductFeedQuery.Data?): List<ProductEntry> =
        responseData?.products?.let {
            ProductEntryMapper().map(it.nodes)
        } ?: emptyList()

    private fun getNextFeedRequest(responseData: ProductFeedQuery.Data?): FeedRequest? {
        val hasNextPage = responseData?.products?.pageInfo?.hasNextPage ?: false
        return if (!hasNextPage) {
            null
        } else {
            FeedRequest(
                hasNextPage = hasNextPage,
                pageToken = responseData?.products?.pageInfo?.endCursor
            )
        }
    }
}