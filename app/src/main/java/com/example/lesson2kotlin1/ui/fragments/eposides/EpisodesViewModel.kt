package com.example.lesson2kotlin1.ui.fragments.eposides

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lesson2kotlin1.base.BaseViewModel
import com.example.lesson2kotlin1.data.repositories.EpisodesRepository
import com.example.lesson2kotlin1.models.RickAndMortyResponse
import com.example.lesson2kotlin1.models.episode.RickAndMortyEpisode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodesRepository: EpisodesRepository
) : BaseViewModel() {
    var isLoading: Boolean = false
    private var page: Int = 0


    private val _episodesState = MutableLiveData<RickAndMortyResponse<RickAndMortyEpisode>>()
    var episodesState: LiveData<RickAndMortyResponse<RickAndMortyEpisode>> = _episodesState

    private val _episodesLocalState = MutableLiveData<List<RickAndMortyEpisode>>()
    var episodesLocalState: LiveData<List<RickAndMortyEpisode>> = _episodesLocalState

    fun fetchEpisodes() {
        isLoading = true
        episodesRepository.fetchEpisode(page).collect(_episodesState) {
            page++
            isLoading = false
        }
    }

    fun getEpisodes() = episodesRepository.getEpisode().collect(_episodesLocalState) }

