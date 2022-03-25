package com.example.lesson2kotlin1.data.remote.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lesson2kotlin1.data.remote.apiservices.CharactersApiService
import com.example.lesson2kotlin1.models.character.RickAndMortyCharacter
import retrofit2.HttpException
import java.io.IOException

const val CHARACTER_KEY = 1

class CharacterPagingSource(private val service: CharactersApiService) :
PagingSource<Int, RickAndMortyCharacter>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortyCharacter> {
        val page = params.key ?: CHARACTER_KEY
        return try {
            val response = service.fetchCharacters(page)
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

    override fun getRefreshKey(state: PagingState<Int, RickAndMortyCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}