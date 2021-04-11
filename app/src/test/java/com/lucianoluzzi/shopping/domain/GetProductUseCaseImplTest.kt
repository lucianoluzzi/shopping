package com.lucianoluzzi.shopping.domain

import com.apollographql.apollo.exception.ApolloException
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.shopping.data.ProductRepository
import com.lucianoluzzi.shopping.data.Response
import com.lucianoluzzi.shopping.domain.model.Product
import com.lucianoluzzi.shopping.domain.model.Seller
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@DisplayName("Given get product use case")
internal class GetProductUseCaseImplTest {
    private val repository = mock<ProductRepository>()
    private val useCase = GetProductUseCaseImpl(repository)
    private val productId = "productId"

    @Nested
    @DisplayName("when repository returns product")
    inner class Product {

        @Test
        fun `then response with product is returned`() = runBlockingTest {
            val expectedReturn = Response.Success(getMockProduct())

            whenever(repository.getProduct(productId)).thenReturn(expectedReturn)
            val actualReturn = useCase.getProduct(productId)

            assertThat(expectedReturn).isEqualTo(actualReturn)
        }
    }

    @Nested
    @DisplayName("when repository returns null")
    inner class Null {

        @Test
        fun `then response is Error`() = runBlockingTest {
            whenever(repository.getProduct(productId)).thenReturn(null)

            val response = useCase.getProduct(productId)
            assertThat(response).isInstanceOf(Response.Error::class.java)
        }
    }

    @Nested
    @DisplayName("when repository throws")
    inner class ExceptionThrown {

        @Test
        fun `then response is Error`() = runBlockingTest {
            val exception = ApolloException("exception message")

            whenever(repository.getProduct(productId)).thenThrow(exception)
            val response = useCase.getProduct(productId)

            assertThat(response).isInstanceOf(Response.Error::class.java)
            val errorResponse = response as Response.Error
            assertThat(errorResponse.errorMessage).isEqualTo(exception.message)
        }
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