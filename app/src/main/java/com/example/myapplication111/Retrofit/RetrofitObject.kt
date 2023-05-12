package com.example.myapplication111.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    var retrofit = Retrofit.Builder().baseUrl("https://www.thecocktaildb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()
}
//1. www.thecocktaildb.com/api/json/v1/1/list.php?c=list  - List the categories
//2. www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail - Filter by Category
//3. www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita - Search cocktail by name
//4. www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007 - Lookup full cocktail details by id
//5. www.thecocktaildb.com/api/json/v1/1/lookup.php?iid=552 - Lookup ingredient by ID
