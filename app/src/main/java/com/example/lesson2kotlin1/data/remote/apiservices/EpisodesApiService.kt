package com.example.lesson2kotlin1.data.remote.apiservices

import com.example.lesson2kotlin1.models.RickAndMortyResponse
import com.example.lesson2kotlin1.models.episode.RickAndMortyEpisode
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesApiService {

    @GET("api/episode")
    suspend fun fetchEpisodes(@Query("page") page: Int): RickAndMortyResponse<RickAndMortyEpisode>
}