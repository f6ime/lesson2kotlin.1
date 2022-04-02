package com.example.lesson2kotlin1.ui.fragments.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lesson2kotlin1.base.view_model_base.BaseViewModel
import com.example.lesson2kotlin1.common.resourse.Resource
import com.example.lesson2kotlin1.data.repositories.CharacterRepository
import com.example.lesson2kotlin1.models.character.RickAndMortyCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseViewModel() {
    var isLoading: Boolean = false
    private var page: Int = 0

    private val _characterState = MutableLiveData<ArrayList<RickAndMortyCharacter>>()
    var characterState: LiveData<ArrayList<RickAndMortyCharacter>> = _characterState
    fun fetchCharacters() {
        isLoading = true
        viewModelScope.launch {
            characterRepository.fetchCharacters(page).collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = true
                    }
                    is Resource.Error -> {
                        Log.e("ololo", it.message.toString())
                    }
                    is Resource.Success -> {
                        if (it.data?.info?.next != null) {
                            isLoading = false
                            _characterState.postValue(it.data.results)
                            page++
                        }
                    }
                }
            }
        }
    }

    init {
        _characterState.value.let {
            fetchCharacters()
        }
    }
}
