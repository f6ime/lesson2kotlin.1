package com.example.lesson2kotlin1.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lesson2kotlin1.base.BaseViewModel
import com.example.lesson2kotlin1.data.repositories.CharacterRepository
import com.example.lesson2kotlin1.models.RickAndMortyResponse
import com.example.lesson2kotlin1.models.character.RickAndMortyCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseViewModel() {

    var page = 1
    var isLoading: Boolean = false

    private val _characterState = MutableLiveData<RickAndMortyResponse<RickAndMortyCharacter>>()
    val characterState: LiveData<RickAndMortyResponse<RickAndMortyCharacter>> = _characterState

    private val _characterLocalState = MutableLiveData<List<RickAndMortyCharacter>>()
    val characterLocalState: LiveData<List<RickAndMortyCharacter>> = _characterLocalState

    fun fetchCharacters() {
        isLoading = true
        characterRepository.fetchCharacter(page).collect(_characterState) {
            page++
            isLoading = false
        }
    }

    fun getCharacters() = characterRepository.getCharacter().collect(_characterLocalState)
}
