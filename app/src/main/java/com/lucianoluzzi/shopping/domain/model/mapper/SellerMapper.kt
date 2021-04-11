package com.lucianoluzzi.shopping.domain.model.mapper

import com.lucianoluzzi.shopping.domain.model.Seller

class SellerMapper {
    fun map(seller: SingleProductQuery.Seller) = Seller(
        id = seller.id,
        name = seller.name,
        profileImage = seller.profileImage.url
    )
}