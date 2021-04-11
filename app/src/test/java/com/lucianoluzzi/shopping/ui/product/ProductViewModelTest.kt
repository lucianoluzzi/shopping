package com.lucianoluzzi.shopping.ui.product

import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.shopping.data.Response
import com.lucianoluzzi.shopping.domain.GetProductUseCase
import com.lucianoluzzi.shopping.domain.model.Product
import com.lucianoluzzi.shopping.domain.model.Seller
import com.lucianoluzzi.shopping.extensions.tests.CoroutineScopeExtension
import com.lucianoluzzi.shopping.extensions.tests.InstantExecutorExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
@DisplayName("Given product view model")
@ExtendWith(InstantExecutorExtension::class, CoroutineScopeExtension::class)
internal class ProductViewModelTest {
    private val useCase = mock<GetProductUseCase>()

    @Nested
    @DisplayName("when load product")
    inner class LoadProduct {
        private val productId = "testId"
        private lateinit var viewModel: ProductViewModel

        @BeforeEach
        fun setUp() {
            viewModel = ProductViewModel(
                productId = productId,
                getProductUseCase = useCase
            )
        }

        @Test
        fun `then fist ui state value is LOADING`() = runBlockingTest {
            val observer = mock<Observer<UIState>>()
            val captor = argumentCaptor<UIState>()

            whenever(useCase.getProduct(productId)).thenReturn(Response.Error(""))
            val viewModel = ProductViewModel(
                productId = productId,
                getProductUseCase = useCase
            )
            viewModel.uiState.observeForever(observer)
            verify(observer, atLeastOnce()).onChanged(
                captor.capture()
            )
            viewModel.loadProduct()

            assertThat(captor.firstValue).isEqualTo(UIState.Loading)
        }

        @Test
        fun `then use case called`() = runBlockingTest {
            whenever(useCase.getProduct(productId)).thenReturn(Response.Error(""))
            viewModel.loadProduct()
            verify(useCase).getProduct(productId)
        }

        @Nested
        @DisplayName("when use case returns error")
        inner class Error {

            @Test
            fun `then error ui state emitted`() = runBlockingTest {
                val errorMessage = "this is the error message"

                whenever(useCase.getProduct(productId)).thenReturn(Response.Error(errorMessage))
                viewModel.loadProduct()

                assertThat(viewModel.uiState.value).isInstanceOf(UIState.Error::class.java)
                val errorState = viewModel.uiState.value as UIState.Error
                assertThat(errorState.errorMessage).isEqualTo(errorMessage)
            }
        }

        @Nested
        @DisplayName("when use case return success")
        inner class Success {

            @Test
            fun `then success state emitted`() = runBlockingTest {
                val expectedProduct = getMockProduct()

                whenever(useCase.getProduct(productId)).thenReturn(Response.Success(expectedProduct))
                viewModel.loadProduct()

                assertThat(viewModel.uiState.value).isInstanceOf(UIState.Success::class.java)
                val successState = viewModel.uiState.value as UIState.Success
                assertThat(successState.data).isEqualTo(expectedProduct)
            }

            private fun getMockProduct() = Product(
                id = "id",
                images = emptyList(),
                description = "description",
                price = 0f,
                currency = "BRL",
                seller = Seller(
                    id = "id",
                    name = "name",
                    profileImage = "profileImage"
                )
            )
        }
    }
}