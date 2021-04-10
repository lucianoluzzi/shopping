package com.lucianoluzzi.shopping.ui.feed

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.shopping.domain.GetFeedUseCase
import com.lucianoluzzi.shopping.domain.model.ProductEntry
import kotlinx.coroutines.flow.emptyFlow
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@DisplayName("Given feed use case")
internal class FeedViewModelTest {
    private val getFeedUseCase = mock<GetFeedUseCase>()
    private val viewModel = FeedViewModel(getFeedUseCase)

    @Nested
    @DisplayName("when getFeed called")
    inner class GetFeed {

        @Test
        fun `then feed use case called`() {
            viewModel.getFeed()
            verify(getFeedUseCase).getFeed()
        }

        @Test
        fun `then returns value from GetFeedUseCase`() {
            val expectedReturnData = emptyFlow<PagingData<ProductEntry>>()
            whenever(getFeedUseCase.getFeed()).thenReturn(expectedReturnData)

            val actualReturnData = viewModel.getFeed()
            assertThat(expectedReturnData).isEqualTo(actualReturnData)
        }
    }
}