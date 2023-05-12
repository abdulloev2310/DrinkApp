package com.example.myapplication111.Retrofit.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "drink_table")
data class DrinkDto2 (
    val strDrink:String,
    val strDrinkThumb: String,
    val idDrink:Int,

):Serializable{
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}