package com.example.lesson2kotlin1.ui.fragments.viewModels

import com.example.lesson2kotlin1.base.view_model_base.BaseViewModel
import com.example.lesson2kotlin1.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseViewModel() {


    fun fetchCharacters() =
        characterRepository.fetchCharacters()


}

