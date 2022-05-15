package com.example.lesson2kotlin1.data.repositories

import com.example.lesson2kotlin1.base.BaseRepository
import com.example.lesson2kotlin1.data.local.db.daos.LocationDao
import com.example.lesson2kotlin1.data.remote.apiservices.LocationsApiService
import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val locationsApiService: LocationsApiService,
    private val locationDao: LocationDao
) : BaseRepository() {

    fun fetchLocation(page: Int) = doRequest(
        { locationsApiService.fetchLocation(page) },

        { locations ->
            locationDao.insertLocation(*locations.results.toTypedArray())
        }
    )

    fun getLocation() = doRequest {
        locationDao.getLocation()
    }
}