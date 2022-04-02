package com.example.lesson2kotlin1.data.remote.apiservices

import com.example.lesson2kotlin1.models.RickAndMortyResponse
import com.example.lesson2kotlin1.models.location.RickAndMortyLocation
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsApiService {

    @GET("api/location")
    suspend fun fetchLocation(@Query("page") page: Int): RickAndMortyResponse<RickAndMortyLocation>
}