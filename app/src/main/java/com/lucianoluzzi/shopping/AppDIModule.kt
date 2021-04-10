package com.lucianoluzzi.shopping

import com.lucianoluzzi.shopping.data.ApolloClientProvider
import com.lucianoluzzi.shopping.data.ProductRepository
import com.lucianoluzzi.shopping.data.ProductRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single { ApolloClientProvider().apolloClient }

    single<ProductRepository> {
        ProductRepositoryImpl(
            apolloClient = get()
        )
    }
}