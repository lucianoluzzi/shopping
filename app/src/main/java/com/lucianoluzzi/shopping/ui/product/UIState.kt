package com.lucianoluzzi.shopping.ui.product

import com.lucianoluzzi.shopping.domain.model.Product

sealed class UIState {
    object Loading : UIState()
    data class Error(val errorMessage: String) : UIState()
    data class Success(val data: Product) : UIState()
}
