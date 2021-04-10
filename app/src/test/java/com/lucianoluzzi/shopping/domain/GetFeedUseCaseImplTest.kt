package com.lucianoluzzi.shopping.domain

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.shopping.data.ProductRepository
import com.lucianoluzzi.shopping.domain.model.ProductEntry
import kotlinx.coroutines.flow.emptyFlow
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@DisplayName("Given feed use case")
internal class GetFeedUseCaseImplTest {

    private val repository = mock<ProductRepository>()
    private val getFeedUseCaseImpl = GetFeedUseCaseImpl(repository)

    @Nested
    @DisplayName("When getFeed called")
    inner class Feed {

        @Test
        fun `then repository called`() {
            getFeedUseCaseImpl.getFeed()
            verify(repository).getProducts(anyInt())
        }

        @Test
        fun `then value from repository returned`() {
            val expectedReturnData = emptyFlow<PagingData<ProductEntry>>()
            whenever(repository.getProducts(anyInt())).thenReturn(expectedReturnData)

            val returnData = getFeedUseCaseImpl.getFeed()
            assertThat(expectedReturnData).isEqualTo(returnData)
        }
    }
}