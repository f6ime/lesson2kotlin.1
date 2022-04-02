package com.example.lesson2kotlin1.ui.fragments.eposides

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lesson2kotlin1.base.view_model_base.BaseViewModel
import com.example.lesson2kotlin1.common.resourse.Resource
import com.example.lesson2kotlin1.data.repositories.EpisodesRepository
import com.example.lesson2kotlin1.models.episode.RickAndMortyEpisode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodesRepository: EpisodesRepository
) : BaseViewModel() {
    var isLoading: Boolean = false
    private var page: Int = 0

    private val _episodesState = MutableLiveData<ArrayList<RickAndMortyEpisode>>()
    var episodesState: LiveData<ArrayList<RickAndMortyEpisode>> = _episodesState
    fun fetchEpisodes() {
        isLoading = true
        viewModelScope.launch {
            episodesRepository.fetchEpisodes(page).collect {
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
                            _episodesState.postValue(it.data.results)
                            page++
                        }
                    }
                }
            }
        }
    }

    init {
        if (_episodesState.value == null) {
            fetchEpisodes()
        }
    }
}