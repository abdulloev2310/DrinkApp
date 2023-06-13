package com.example.myapplication111.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication111.R
import com.example.myapplication111.Retrofit.InterFaceApi
import com.example.myapplication111.Retrofit.RetrofitObject
import com.example.myapplication111.Retrofit.adapter.AdapterDetailDrink
import com.example.myapplication111.Retrofit.data.Drink
import com.example.myapplication111.Retrofit.data.DrinkDto2
import com.example.myapplication111.Room.Favorite.FavoriteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private var mAdapter:AdapterDetailDrink? = null
    private var a:Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        lifecycleScope.launch {
            val id = intent.getIntExtra("Data", 1)
            val name = intent.getStringExtra("Name") as String
            val thumb = intent.getStringExtra("Thumb") as String
            Log.e( "driink","${id}" + " " + "${name}" + " " + "${thumb}")
            val detailDrink = RetrofitObject.retrofit.create(InterFaceApi::class.java).getDetailDrinkById(id)
            mAdapter = AdapterDetailDrink()
            val rv = findViewById<RecyclerView>(R.id.rvDetailDrink)
            rv.apply {
                layoutManager = LinearLayoutManager(this@DetailActivity)
                adapter = mAdapter.apply {
                    setAdapter(detailDrink.drinks)
                }
            }



            val favorite = findViewById<FloatingActionButton>(R.id.favoriteButton)
            favorite.setOnClickListener {



//                    if(a == 1){
lifecycleScope.launch {
    val drink = DrinkDto2(
        name, thumb, id
    )
    FavoriteDatabase(this@DetailActivity).getFavoriteDao().addFavoriteDrink(drink)
    Toast.makeText(this@DetailActivity, "Add", Toast.LENGTH_SHORT).show()
}
                        a = 0
//                    }else{
//                        lifecycleScope.launch {
//                            val drink = DrinkDto2(
//                                name!!, thumb!!, id
//                            )
//                            FavoriteDatabase(this@DetailActivity).getFavoriteDao()
//                                .deleteDrink(drink)
//                            Toast.makeText(this@DetailActivity, "Delete", Toast.LENGTH_SHORT).show()
//                        }
//                }

            }



        }






    }

    private fun setAdapter(list:List<Drink>){
        mAdapter?.setData(list)
    }
}