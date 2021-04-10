package com.lucianoluzzi.shopping.domain.model

data class FeedRequest(
    val hasNextPage: Boolean,
    val pageToken: String? = null
)
