package com.lucianoluzzi.shopping.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.shopping.data.Response
import com.lucianoluzzi.shopping.domain.GetProductUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productId: String,
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UIState>().apply {
        value = UIState.Loading
    }
    val uiState: LiveData<UIState> = _uiState

    fun loadProduct() {
        viewModelScope.launch {
            when (val response = getProductUseCase.getProduct(productId)) {
                is Response.Error -> _uiState.value = UIState.Error(response.errorMessage)
                is Response.Success -> _uiState.value = UIState.Success(response.data)
            }
        }
    }
}