package com.example.lesson2kotlin1.models

data class Info(
    val count: Int,
    val next: String? = null,
    val pages: Int? = null,
    val prev: Any? = null
)