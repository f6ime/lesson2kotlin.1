package com.example.lesson2kotlin1.ui.fragments.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lesson2kotlin1.base.BaseViewModel
import com.example.lesson2kotlin1.data.repositories.LocationsRepository
import com.example.lesson2kotlin1.models.RickAndMortyResponse
import com.example.lesson2kotlin1.models.location.RickAndMortyLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationsRepository: LocationsRepository
) : BaseViewModel() {
    var isLoading: Boolean = false
    private var page: Int = 0

    private val _locationsState = MutableLiveData<RickAndMortyResponse<RickAndMortyLocation>>()
    var locationState: LiveData<RickAndMortyResponse<RickAndMortyLocation>> = _locationsState

    private val _localLocationState = MutableLiveData<List<RickAndMortyLocation>>()
    var LocationLocalState: LiveData<List<RickAndMortyLocation>> = _localLocationState


    fun fetchLocations() {
        isLoading = true
        locationsRepository.fetchLocation(page).collect(_locationsState) {
            page++
            isLoading = false
        }
    }

    fun getLocations() = locationsRepository.getLocation().collect(_localLocationState) }
