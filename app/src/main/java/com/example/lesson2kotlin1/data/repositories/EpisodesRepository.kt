package com.example.lesson2kotlin1.data.repositories

import androidx.lifecycle.liveData
import com.example.lesson2kotlin1.common.resourse.Resource
import com.example.lesson2kotlin1.data.remote.apiservices.EpisodesApiService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val episodesApiService: EpisodesApiService
) {
    fun fetchEpisodes() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(episodesApiService.fetchEpisodes()))
        } catch (ioException: Exception) {
            emit(Resource.Error(null, ioException.localizedMessage))
        }
    }
}