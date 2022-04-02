package com.example.lesson2kotlin1.base.diff_model

interface BaseDiffModel {
    val id: Int
    override fun equals(other: Any?): Boolean
}