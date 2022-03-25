package com.example.lesson2kotlin1.data.repositories

import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.lesson2kotlin1.common.resourse.Resource
import com.example.lesson2kotlin1.data.remote.apiservices.CharactersApiService
import com.example.lesson2kotlin1.data.remote.pagingsources.CharacterPagingSource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharactersApiService,
) {
    fun fetchCharacters() = Pager(PagingConfig(pageSize = 20)) {
        CharacterPagingSource(service)
    }.flow


    fun fetchSingleCharacter(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(service.fetchSingleCharacter(id)))
        } catch (ioException: Exception) {
            emit(Resource.Error(null, ioException.localizedMessage))
        }
    }
}