package com.example.lesson2kotlin1.models.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lesson2kotlin1.base.diff_model.BaseDiffModel
import com.google.gson.annotations.SerializedName
@Entity(tableName = "location")
data class RickAndMortyLocation(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String
) : BaseDiffModel