package com.example.lesson2kotlin1.models.character


import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)