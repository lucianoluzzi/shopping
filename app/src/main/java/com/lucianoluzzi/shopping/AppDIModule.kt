package com.lucianoluzzi.shopping

import com.lucianoluzzi.shopping.data.ApolloClientProvider
import com.lucianoluzzi.shopping.data.ProductRepository
import com.lucianoluzzi.shopping.data.ProductRepositoryImpl
import com.lucianoluzzi.shopping.domain.GetFeedUseCase
import com.lucianoluzzi.shopping.domain.GetFeedUseCaseImpl
import com.lucianoluzzi.shopping.domain.GetProductUseCase
import com.lucianoluzzi.shopping.domain.GetProductUseCaseImpl
import com.lucianoluzzi.shopping.ui.feed.FeedViewModel
import com.lucianoluzzi.shopping.ui.product.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ApolloClientProvider().apolloClient }

    single<ProductRepository> {
        ProductRepositoryImpl(
            apolloClient = get()
        )
    }

    single<GetFeedUseCase> {
        GetFeedUseCaseImpl(
            repository = get()
        )
    }
    single<GetProductUseCase> {
        GetProductUseCaseImpl(
            repository = get()
        )
    }

    viewModel {
        FeedViewModel(
            getFeedUseCase = get()
        )
    }
    viewModel {
        ProductViewModel(
            productId = get(),
            getProductUseCase = get()
        )
    }
}