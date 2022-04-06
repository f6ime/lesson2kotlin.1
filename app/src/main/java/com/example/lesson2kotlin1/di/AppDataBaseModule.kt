package com.example.lesson2kotlin1.di

import android.content.Context
import com.example.lesson2kotlin1.data.local.db.AppDatabase
import com.example.lesson2kotlin1.data.local.db.RoomClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDataBaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        RoomClient().provideRoomDatabase(context)

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase) =
        RoomClient().provideCharacterDao(appDatabase)

    @Singleton
    @Provides
    fun provideLocationDao(appDatabase: AppDatabase) =
        RoomClient().provideLocationDao(appDatabase)

    @Singleton
    @Provides
    fun provideEpisodeDao(appDatabase: AppDatabase) =
        RoomClient().provideEpisodeDao(appDatabase)

}