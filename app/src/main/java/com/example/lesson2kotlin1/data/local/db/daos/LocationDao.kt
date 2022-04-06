package com.example.lesson2kotlin1.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lesson2kotlin1.models.location.RickAndMortyLocation

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(vararg locations: RickAndMortyLocation)

    @Query("SELECT * FROM location")
    suspend fun getLocation(): List<RickAndMortyLocation>
}