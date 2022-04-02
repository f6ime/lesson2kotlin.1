package com.example.lesson2kotlin1.data.repositories

import com.example.lesson2kotlin1.base.repository_base.BaseRepository
import com.example.lesson2kotlin1.data.remote.apiservices.LocationsApiService
import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val locationsApiService: LocationsApiService
) : BaseRepository() {

    fun fetchLocations(page: Int) = sendingARequest {
        locationsApiService.fetchLocation(page)
    }
}