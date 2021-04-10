package com.lucianoluzzi.shopping.data

import com.apollographql.apollo.ApolloClient

class ApolloClientProvider {
    val apolloClient = ApolloClient.builder()
        .serverUrl(BASE_URL)
        .build()

    private companion object {
        private const val BASE_URL = "http://localhost:4000/graphql"
    }
}