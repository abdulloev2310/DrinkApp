package com.example.myapplication111.Room.Favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication111.Retrofit.data.DrinkDto2
import retrofit2.http.DELETE

@Dao
interface FavoriteDto {

    @Insert
    suspend fun addFavoriteDrink(drinkDto2: DrinkDto2)

    @Query("SELECT * FROM drink_table")
    suspend fun getAll():List<DrinkDto2>

    @DELETE
    suspend fun deleteDrink(drinkDto2: DrinkDto2)


}