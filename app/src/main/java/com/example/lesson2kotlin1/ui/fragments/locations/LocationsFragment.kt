package com.example.lesson2kotlin1.ui.fragments.locations

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2kotlin1.R
import com.example.lesson2kotlin1.adapters.LocationsAdapter
import com.example.lesson2kotlin1.base.BaseFragment
import com.example.lesson2kotlin1.common.resourse.Resource
import com.example.lesson2kotlin1.databinding.FragmentLocationsBinding
import com.example.lesson2kotlin1.ui.fragments.viewModels.LocationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding, LocationsViewModel>(
    R.layout.fragment_locations
) {
    override val viewModel: LocationsViewModel by viewModels()
    override val binding by viewBinding(FragmentLocationsBinding::bind)
    private val adapter = LocationsAdapter()

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {

        binding.recyclerview.adapter = adapter
    }

    override fun setupObserver() {
        subscribeToLocations()
    }
    private fun subscribeToLocations() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchLocations().observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        Log.e("gaypop", "subscribeToLocations: ")
                    }
                    is Resource.Error -> {
                        Log.e("gaypop", it.message.toString())
                    }
                    is Resource.Success -> {
                        it.data?.results?.let { it1 -> adapter.submitList(it1) }

                    }
                }
            }
        }
    }
}