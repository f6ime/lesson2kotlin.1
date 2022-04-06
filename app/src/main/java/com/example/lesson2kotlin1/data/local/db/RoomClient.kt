package com.example.lesson2kotlin1.data.local.db

import android.content.Context
import androidx.room.Room
import com.example.lesson2kotlin1.data.local.db.daos.CharacterDao
import com.example.lesson2kotlin1.data.local.db.daos.EpisodeDao
import com.example.lesson2kotlin1.data.local.db.daos.LocationDao

class RoomClient {

    fun provideRoomDatabase(context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, "database"
    ).fallbackToDestructiveMigration().build()

    fun provideCharacterDao(characterDatabase: AppDatabase): CharacterDao = characterDatabase.characterDao()

    fun provideEpisodeDao(episodeDatabase: AppDatabase): EpisodeDao = episodeDatabase.episodeDao()

    fun provideLocationDao(locationDatabase: AppDatabase): LocationDao = locationDatabase.locationDao()
}