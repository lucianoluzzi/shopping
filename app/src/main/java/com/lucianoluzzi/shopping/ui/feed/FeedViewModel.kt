package com.lucianoluzzi.shopping.ui.feed

import androidx.lifecycle.ViewModel
import com.lucianoluzzi.shopping.domain.GetFeedUseCase
import kotlinx.coroutines.flow.distinctUntilChanged

class FeedViewModel(
    private val getFeedUseCase: GetFeedUseCase
) : ViewModel() {

    fun getFeed() = getFeedUseCase.getFeed()
}