package com.example.lesson2kotlin1.ui.fragments.characters.details

import com.example.lesson2kotlin1.base.view_model_base.BaseViewModel
import com.example.lesson2kotlin1.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseViewModel() {

    fun fetchSingleCharacter(id: Int) =
        characterRepository.fetchSingleCharacter(id)
}