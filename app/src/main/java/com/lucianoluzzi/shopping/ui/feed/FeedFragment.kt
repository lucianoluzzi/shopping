package com.lucianoluzzi.shopping.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.lucianoluzzi.shopping.databinding.FeedFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : Fragment() {
    private lateinit var binding: FeedFragmentBinding
    private val viewModel by viewModel<FeedViewModel>()
    private val feedAdapter = FeedAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FeedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.products.adapter = feedAdapter
        getFeed()
    }

    private fun getFeed() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getFeed().collectLatest {
                feedAdapter.submitData(it)
            }
        }
    }
}