package com.example.lesson2kotlin1.common.extension

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

fun <T, D : RecyclerView.ViewHolder> ListAdapter<T, D>.sendData(data: List<T>) {
    val newList = ArrayList<T>(currentList)
    newList.addAll(data)
    submitList(
        newList
    )
}