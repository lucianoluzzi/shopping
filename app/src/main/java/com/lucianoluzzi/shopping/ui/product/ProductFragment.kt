package com.lucianoluzzi.shopping.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lucianoluzzi.shopping.databinding.ProductFragmentBinding
import com.lucianoluzzi.shopping.domain.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductFragment : Fragment() {
    private lateinit var binding: ProductFragmentBinding
    private val navigationArgs: ProductFragmentArgs by navArgs()
    private val viewModel by viewModel<ProductViewModel> {
        parametersOf(navigationArgs.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observe(viewLifecycleOwner) {
            handleUIState(it)
        }
        viewModel.loadProduct()
    }

    private fun handleUIState(uiState: UIState) {
        when (uiState) {
            is UIState.Error -> {
            }
            is UIState.Loading -> {
            }
            is UIState.Success -> setViews(uiState.data)
        }
    }

    private fun setViews(product: Product) = with(binding) {
        imagePager.adapter = ImageSliderAdapter(this@ProductFragment, product.images)
        description.text = product.description
        price.text = "${product.currency} ${product.price}"
    }
}