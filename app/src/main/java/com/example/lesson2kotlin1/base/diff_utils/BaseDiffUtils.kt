package com.example.lesson2kotlin1.base.diff_utils

import androidx.recyclerview.widget.DiffUtil
import com.example.lesson2kotlin1.base.diff_model.BaseDiffModel

class BaseDiffUtils<T : BaseDiffModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem == newItem
}