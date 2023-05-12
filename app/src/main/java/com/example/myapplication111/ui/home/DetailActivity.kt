package com.example.myapplication111.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
import kotlinx.coroutines.launch
import retrofit2.create

class DetailActivity : AppCompatActivity() {
    private var mAdapter:AdapterDetailDrink? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        lifecycleScope.launch {
            val id = intent.getIntExtra("Data",1)
            val detailDrink = RetrofitObject.retrofit.create(InterFaceApi::class.java).getDetailDrinkById(id)
            mAdapter = AdapterDetailDrink()
            val rv = findViewById<RecyclerView>(R.id.rvDetailDrink)
            rv.apply {
                layoutManager = LinearLayoutManager(this@DetailActivity)
                adapter = mAdapter.apply {
                    setAdapter(detailDrink.drinks)
                }
            }







        }






    }

    private fun setAdapter(list:List<Drink>){
        mAdapter?.setData(list)
    }
}