package com.lucianoluzzi.shopping.ui.feed

import androidx.lifecycle.ViewModel
import com.lucianoluzzi.shopping.domain.GetFeedUseCase

class FeedViewModel(
    private val getFeedUseCase: GetFeedUseCase
) : ViewModel() {

    fun getFeed() = getFeedUseCase.getFeed()
}