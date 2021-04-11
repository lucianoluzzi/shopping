package com.lucianoluzzi.shopping.domain

import com.lucianoluzzi.shopping.data.ProductRepository
import com.lucianoluzzi.shopping.data.Response
import com.lucianoluzzi.shopping.domain.model.Product

class GetProductUseCaseImpl(
    private val repository: ProductRepository
) : GetProductUseCase {

    override suspend fun getProduct(productId: String): Response<Product> {
        try {
            val product = repository.getProduct(productId)
            product?.let {
                return it
            }
        } catch (exception: Exception) {
            return Response.Error(exception.message ?: NOT_FOUND_MESSAGE)
        }

        return Response.Error(NOT_FOUND_MESSAGE)
    }

    private companion object {
        private const val NOT_FOUND_MESSAGE = "The page you are looking for doens't exists..."
    }
}