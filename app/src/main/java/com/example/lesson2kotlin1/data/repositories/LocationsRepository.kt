package com.example.lesson2kotlin1.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.lesson2kotlin1.data.remote.apiservices.LocationsApiService
import com.example.lesson2kotlin1.data.remote.pagingsources.LocationPagingSource
import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val service: LocationsApiService
) {
    fun fetchLocations() = Pager(PagingConfig(pageSize = 10)) {
        LocationPagingSource(service)
    }.flow
}