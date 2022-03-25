package com.example.lesson2kotlin1.ui.fragments.viewModels

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.lesson2kotlin1.base.view_model_base.BaseViewModel
import com.example.lesson2kotlin1.data.repositories.LocationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationsRepository: LocationsRepository
) : BaseViewModel() {

    fun fetchLocations() = locationsRepository.fetchLocations().cachedIn(viewModelScope)
}