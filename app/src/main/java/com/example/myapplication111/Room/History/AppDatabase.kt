package com.example.myapplication111.Room.History

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication111.Retrofit.data.DrinkDto2


@Database(entities = [DrinkDto2::class], version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getDrinkDao(): DrinkDao

    companion object{

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(this){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {

            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
        }

    }

}