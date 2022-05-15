package com.example.lesson2kotlin1.data.repositories

import com.example.lesson2kotlin1.base.BaseRepository
import com.example.lesson2kotlin1.data.local.db.daos.CharacterDao
import com.example.lesson2kotlin1.data.remote.apiservices.CharactersApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val charactersApiService: CharactersApiService,
    private val characterDao: CharacterDao
) : BaseRepository() {
    fun fetchCharacter(page: Int) = doRequest(
        { charactersApiService.fetchCharacters(page) },
        { characters ->
            characterDao.insertAll(*characters.results.toTypedArray())
        }
    )

    fun fetchSingleCharacter(id: Int) = doRequest() {
        charactersApiService.fetchSingleCharacter(id)
    }

    fun getCharacter() = doRequest() {
        characterDao.getAll()
    }
}