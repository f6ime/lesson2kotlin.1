package com.example.lesson2kotlin1.data.remote.apiservices

import com.example.lesson2kotlin1.data.remote.models.RickAndMortyResponse
import com.example.lesson2kotlin1.data.remote.models.location.RickAndMortyLocation
import retrofit2.http.GET

interface LocationsApiService {

    @GET("api/location")
    suspend fun fetchEpisode(): RickAndMortyResponse<RickAndMortyLocation>
}