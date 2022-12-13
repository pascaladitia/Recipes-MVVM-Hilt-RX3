package com.pascal.recipes.di

import android.app.Application
import androidx.room.Room
import com.pascal.recipes.data.local.db.FavoriteDao
import com.pascal.recipes.data.local.db.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDb(application: Application): FavoriteDatabase {
        return Room.databaseBuilder(
            application,
            FavoriteDatabase::class.java,
            "favorite.db"
        ).build()
    }

    // providing your dao interface to be injected into TripRepository
    @Provides
    @Singleton
    fun provideTripDao(
        FavoriteDatabase: FavoriteDatabase
    ): FavoriteDao = FavoriteDatabase.favDao
    
}