package com.example.lesson2kotlin1.data.remote.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lesson2kotlin1.data.remote.apiservices.LocationsApiService
import com.example.lesson2kotlin1.models.location.RickAndMortyLocation
import retrofit2.HttpException
import java.io.IOException

const val LOCATION_KEY = 1

class LocationPagingSource(private val service: LocationsApiService) :
    PagingSource<Int, RickAndMortyLocation>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortyLocation> {
        val page = params.key ?: LOCATION_KEY
        return try {
            val response = service.fetchLocation(page)
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

    override fun getRefreshKey(state: PagingState<Int, RickAndMortyLocation>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
