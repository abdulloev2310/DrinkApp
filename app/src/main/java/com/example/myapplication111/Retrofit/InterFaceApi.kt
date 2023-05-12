package com.example.myapplication111.Retrofit

import com.example.myapplication111.Retrofit.data.CategoryDto
import com.example.myapplication111.Retrofit.data.DrinkDto
import com.example.myapplication111.Retrofit.data.Drrr
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InterFaceApi {

    @GET("filter.php?c")
    suspend fun getCategories(@Query("c") category: String): DrinkDto

    @GET("list.php?c=list")
    suspend fun getCategory():CategoryDto

    @GET("lookup.php?i")
    suspend fun getDetailDrinkById(@Query("i") id:Int): Drrr

    @GET("search.php?s")
    suspend fun searchDrink(@Query("s") search:String):DrinkDto
}