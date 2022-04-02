package com.example.lesson2kotlin1.models.episode

import com.example.lesson2kotlin1.base.diff_model.BaseDiffModel
import com.google.gson.annotations.SerializedName

data class RickAndMortyEpisode(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val air_date: String,
    @SerializedName("episodes")
    val episode: String
) : BaseDiffModel