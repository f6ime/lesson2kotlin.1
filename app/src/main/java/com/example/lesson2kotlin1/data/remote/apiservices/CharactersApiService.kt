package com.example.lesson2kotlin1.data.remote.apiservices

import com.example.lesson2kotlin1.data.remote.models.RickAndMortyResponse
import com.example.lesson2kotlin1.data.remote.models.character.RickAndMortyCharacter
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApiService {

    @GET("api/character")
    suspend fun fetchCharacters(): RickAndMortyResponse<RickAndMortyCharacter>

    @GET("api/character/{id}")
    suspend fun fetchSingleCharacter(@Path("id") id: Int): RickAndMortyCharacter
}