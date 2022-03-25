package com.example.lesson2kotlin1.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.lesson2kotlin1.data.remote.apiservices.EpisodesApiService
import com.example.lesson2kotlin1.data.remote.pagingsources.EpisodePagingSource
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val service: EpisodesApiService
) {
    fun fetchEpisodes() = Pager(PagingConfig(pageSize = 10)) {
        EpisodePagingSource(service)
    }.flow
}