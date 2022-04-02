package com.example.lesson2kotlin1.data.repositories

import com.example.lesson2kotlin1.base.repository_base.BaseRepository
import com.example.lesson2kotlin1.data.remote.apiservices.CharactersApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val charactersApiService: CharactersApiService,
) : BaseRepository() {

    fun fetchCharacters(page: Int) = sendingARequest {
        charactersApiService.fetchCharacters(page)
    }

    fun fetchSingleCharacter(id: Int) = sendingARequest {
        charactersApiService.fetchSingleCharacter(id)
    }
}