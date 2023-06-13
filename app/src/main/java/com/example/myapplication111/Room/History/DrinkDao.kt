package com.example.myapplication111.Room.History

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication111.Retrofit.data.DrinkDto
import com.example.myapplication111.Retrofit.data.DrinkDto2
import retrofit2.http.DELETE

@Dao
interface DrinkDao {

    @Insert
    suspend fun addDrinkToFragment(drinkDto2: DrinkDto2)

    @Query("SELECT * FROM drink_table")
    suspend fun getAllDrinks():List<DrinkDto2>


}