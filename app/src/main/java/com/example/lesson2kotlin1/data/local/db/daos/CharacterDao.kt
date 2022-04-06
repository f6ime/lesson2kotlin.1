package com.example.lesson2kotlin1.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lesson2kotlin1.models.character.RickAndMortyCharacter

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg characters: RickAndMortyCharacter)

    @Query("SELECT * FROM character ")
    suspend fun getAll(): List<RickAndMortyCharacter>
}