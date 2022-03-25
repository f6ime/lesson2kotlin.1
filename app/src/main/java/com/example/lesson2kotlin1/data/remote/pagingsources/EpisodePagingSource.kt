package com.example.lesson2kotlin1.data.remote.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lesson2kotlin1.data.remote.apiservices.EpisodesApiService
import com.example.lesson2kotlin1.models.episode.RickAndMortyEpisode
import retrofit2.HttpException
import java.io.IOException

const val EPISODE_KEY = 1

class EpisodePagingSource (private val service: EpisodesApiService) :
    PagingSource<Int, RickAndMortyEpisode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortyEpisode> {
        val page = params.key ?: EPISODE_KEY
        return try {
            val response = service.fetchEpisodes(page)
            val nextPageNumber = Uri.parse(response.info.next).getQueryParameter("page")!!.toInt()
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RickAndMortyEpisode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}
