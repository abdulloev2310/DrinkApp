package com.example.myapplication111.Room.Favorite

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication111.Room.History.AppDatabase
import com.example.myapplication111.Room.History.DrinkDao

abstract class FavoriteDatabase:RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDto

    companion object{
        @Volatile
        private var instance: FavoriteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(this){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context): FavoriteDatabase {

            return Room.databaseBuilder(
                context.applicationContext,
                FavoriteDatabase::class.java,
                "favorite_database"
            ).build()
        }
    }

}