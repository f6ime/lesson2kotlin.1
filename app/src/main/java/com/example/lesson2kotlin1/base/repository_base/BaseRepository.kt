package com.example.lesson2kotlin1.base.repository_base

import com.example.lesson2kotlin1.common.resourse.Resource
import kotlinx.coroutines.flow.flow
import java.io.IOException

open class BaseRepository {
    protected fun <T> sendingARequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (e: IOException) {
            emit(
                Resource.Error(null, e.localizedMessage ?: "ERROR")
            )
        }
    }
}