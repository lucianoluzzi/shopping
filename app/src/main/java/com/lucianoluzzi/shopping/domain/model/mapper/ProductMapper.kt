package com.lucianoluzzi.shopping.domain.model.mapper

import SingleProductQuery
import com.lucianoluzzi.shopping.domain.model.Product

class ProductMapper {
    fun map(product: SingleProductQuery.Product) = Product(
        id = product.id,
        images = mutableListOf<String>().also { productList ->
            productList.addAll(product.secondaryImages.map {
                it.url
            })
            productList.add(product.primaryImage.url)
        },
        description = product.brand,
        price = product.price.amount.toFloat(),
        currency = product.price.currency.name,
        seller = SellerMapper().map(product.seller)
    )
}