package com.lucianoluzzi.shopping.domain.model.mapper

import ProductFeedQuery
import com.lucianoluzzi.shopping.domain.model.ProductEntry

class ProductEntryMapper {
    fun map(
        productQueryResult: List<ProductFeedQuery.Node>
    ): List<ProductEntry> = productQueryResult.map { node ->
        ProductEntry(
            id = node.id,
            image = node.primaryImage.url,
            brand = node.brand,
            price = node.price.amount.toFloat(),
            currency = node.price.currency.name
        )
    }
}