package com.example.lesson2kotlin1.ui.fragments.locations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lesson2kotlin1.base.view_model_base.BaseViewModel
import com.example.lesson2kotlin1.common.resourse.Resource
import com.example.lesson2kotlin1.data.repositories.LocationsRepository
import com.example.lesson2kotlin1.models.location.RickAndMortyLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationsRepository: LocationsRepository
) : BaseViewModel() {
    var isLoading: Boolean = false
    private var page: Int = 0
    private val _locationsState = MutableLiveData<ArrayList<RickAndMortyLocation>>()
    var locationState: LiveData<ArrayList<RickAndMortyLocation>> = _locationsState
    fun fetchLocations() {
        isLoading = true
        viewModelScope.launch {
            locationsRepository.fetchLocations(page).collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = true
                    }
                    is Resource.Error -> {
                        Log.e("ololo", it.message.toString())
                    }
                    is Resource.Success -> {
                        if (it.data?.info?.next != null) {
                            isLoading = false
                            _locationsState.postValue(it.data.results)
                            page++
                        }
                    }
                }
            }
        }
    }

    init {
        if (_locationsState.value == null) {
            fetchLocations()
        }
    }
}