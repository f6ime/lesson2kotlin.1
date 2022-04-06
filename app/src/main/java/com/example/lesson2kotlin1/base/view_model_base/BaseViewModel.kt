package com.example.lesson2kotlin1.base.view_model_base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson2kotlin1.common.resourse.Resource
import com.example.lesson2kotlin1.models.RickAndMortyResponse
import com.example.lesson2kotlin1.models.character.RickAndMortyCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    protected fun <T> Flow<Resource<T>>.collect(
        state: MutableLiveData<T>,
        addition: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            collect {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Error -> {
                        Log.e("ololo", it.message.toString())
                    }
                    is Resource.Success -> {
                        addition?.let {
                            addition(
                            )
                        }
                        state.postValue(it.data)
                    }
                }
            }
        }
    }

}