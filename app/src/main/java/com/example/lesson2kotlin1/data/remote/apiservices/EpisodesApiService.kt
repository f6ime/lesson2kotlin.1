package com.example.lesson2kotlin1.data.remote.apiservices

import com.example.lesson2kotlin1.data.remote.models.RickAndMortyResponse
import com.example.lesson2kotlin1.data.remote.models.episode.RickAndMortyEpisode
import retrofit2.http.GET

interface EpisodesApiService {

    @GET("api/episode")
    suspend fun fetchEpisodes(): RickAndMortyResponse<RickAndMortyEpisode>
}