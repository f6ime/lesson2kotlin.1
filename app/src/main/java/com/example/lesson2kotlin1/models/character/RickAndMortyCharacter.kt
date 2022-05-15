package com.example.lesson2kotlin1.models.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lesson2kotlin1.base.BaseDiffModel
import com.google.gson.annotations.SerializedName
@Entity(tableName = "character")
data class RickAndMortyCharacter(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: String,
) : BaseDiffModel