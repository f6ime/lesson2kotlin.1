package com.example.lesson2kotlin1.ui.fragments.locations

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2kotlin1.R
import com.example.lesson2kotlin1.ui.adapters.LocationsAdapter
import com.example.lesson2kotlin1.base.BaseFragment
import com.example.lesson2kotlin1.common.extension.sendData
import com.example.lesson2kotlin1.databinding.FragmentLocationsBinding
import com.example.lesson2kotlin1.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint


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

    private fun setupAdapter() = with(binding.recyclerview) {
        adapter = locationAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager
        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, {
                if (isOnline()) viewModel.fetchLocations()
                else null
            }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    override fun setupObserver() {
        subscribeToLocations()
        subscribeToLocationLocale()
    }

    private fun subscribeToLocations() {
        viewModel.locationState.observe(viewLifecycleOwner) {
            locationAdapter.sendData(it.results)

        }
    }

    private fun subscribeToLocationLocale() {
        viewModel.LocationLocalState.observe(viewLifecycleOwner) {
            locationAdapter.sendData(it)
        }
    }

    override fun setupRequest() {
        if (viewModel.locationState.value == null && isOnline()) viewModel.fetchLocations()
        else viewModel.getLocations()
    }

    fun isOnline(): Boolean {
        val cm =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}