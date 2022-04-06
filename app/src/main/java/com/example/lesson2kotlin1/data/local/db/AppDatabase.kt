package com.example.lesson2kotlin1.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lesson2kotlin1.data.local.db.daos.CharacterDao
import com.example.lesson2kotlin1.data.local.db.daos.EpisodeDao
import com.example.lesson2kotlin1.data.local.db.daos.LocationDao
import com.example.lesson2kotlin1.models.character.RickAndMortyCharacter
import com.example.lesson2kotlin1.models.episode.RickAndMortyEpisode
import com.example.lesson2kotlin1.models.location.RickAndMortyLocation


@Database(
    entities = [
        RickAndMortyCharacter::class,
        RickAndMortyLocation::class,
        RickAndMortyEpisode::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
    abstract fun episodeDao(): EpisodeDao

}