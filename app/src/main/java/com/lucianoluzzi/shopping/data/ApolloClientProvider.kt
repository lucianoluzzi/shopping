package com.lucianoluzzi.shopping.data

import com.apollographql.apollo.ApolloClient

class ApolloClientProvider {
    val apolloClient: ApolloClient = ApolloClient.builder()
        .serverUrl(BASE_URL)
        .build()

    private companion object {
        private const val BASE_URL = "http://10.0.2.2:4000/graphql"
    }
}