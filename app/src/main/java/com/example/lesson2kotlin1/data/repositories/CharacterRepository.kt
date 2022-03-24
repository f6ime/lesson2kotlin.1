package com.example.lesson2kotlin1.data.repositories

import androidx.lifecycle.liveData
import com.example.lesson2kotlin1.common.resourse.Resource
import com.example.lesson2kotlin1.data.remote.apiservices.CharactersApiService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val charactersApiService: CharactersApiService,
) {
    fun fetchCharacters() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(charactersApiService.fetchCharacters()))
        } catch (ioException: Exception) {
            emit(Resource.Error(null, ioException.localizedMessage))
        }
    }

    fun fetchSingleCharacter(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(charactersApiService.fetchSingleCharacter(id)))
        } catch (ioException: Exception) {
            emit(Resource.Error(null, ioException.localizedMessage))
        }
    }
}