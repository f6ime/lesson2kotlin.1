package com.example.lesson2kotlin1.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lesson2kotlin1.models.episode.RickAndMortyEpisode

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(vararg episode: RickAndMortyEpisode)

    @Query("SELECT * FROM episode")
    suspend fun getEpisode(): List<RickAndMortyEpisode>
}