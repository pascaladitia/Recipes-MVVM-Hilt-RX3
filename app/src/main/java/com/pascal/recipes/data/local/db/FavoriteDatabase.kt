package com.pascal.recipes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pascal.recipes.data.local.model.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract val favDao: FavoriteDao

}