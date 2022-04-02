package com.example.lesson2kotlin1.data.repositories

import com.example.lesson2kotlin1.base.repository_base.BaseRepository
import com.example.lesson2kotlin1.data.remote.apiservices.EpisodesApiService
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val episodesApiService: EpisodesApiService
) : BaseRepository() {

    fun fetchEpisodes(page: Int) = sendingARequest {
        episodesApiService.fetchEpisodes(page)
    }
}