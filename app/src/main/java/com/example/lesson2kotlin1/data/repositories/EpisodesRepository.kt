package com.example.lesson2kotlin1.data.repositories

import com.example.lesson2kotlin1.base.BaseRepository
import com.example.lesson2kotlin1.data.local.db.daos.EpisodeDao
import com.example.lesson2kotlin1.data.remote.apiservices.EpisodesApiService
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val episodesApiService: EpisodesApiService,
    private val episodeDao: EpisodeDao
) : BaseRepository() {

    fun fetchEpisode(page: Int) = doRequest(
        { episodesApiService.fetchEpisodes(page) },

        { episodes ->
            episodeDao.insertEpisode(*episodes.results.toTypedArray())
        }
    )

    fun getEpisode() = doRequest() {
        episodeDao.getEpisode()
    }
}