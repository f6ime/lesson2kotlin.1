package com.example.lesson2kotlin1.ui.fragments.locations

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2kotlin1.R
import com.example.lesson2kotlin1.ui.adapters.LocationsAdapter
import com.example.lesson2kotlin1.base.BaseFragment
import com.example.lesson2kotlin1.databinding.FragmentLocationsBinding
import com.example.lesson2kotlin1.ui.adapters.loader.LoadingLoaderStateAdapter
import com.example.lesson2kotlin1.ui.fragments.viewModels.LocationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding, LocationsViewModel>(
    R.layout.fragment_locations
) {
    override val viewModel: LocationsViewModel by viewModels()
    override val binding by viewBinding(FragmentLocationsBinding::bind)
    private val locationAdapter = LocationsAdapter()

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerview.adapter = locationAdapter
        binding.apply {
            recyclerview.adapter = locationAdapter.withLoadStateHeaderAndFooter(
                header = LoadingLoaderStateAdapter { locationAdapter.retry() },
                footer = LoadingLoaderStateAdapter { locationAdapter.retry() }
            )
        }
    }

    override fun setupObserver() {
        subscribeToLocations()
    }

    private fun subscribeToLocations() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchLocations().collectLatest {
                locationAdapter.submitData(it)
            }
        }
    }
}