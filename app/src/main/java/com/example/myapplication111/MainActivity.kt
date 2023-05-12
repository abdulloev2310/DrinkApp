package com.example.myapplication111

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication111.Retrofit.InterFaceApi
import com.example.myapplication111.Retrofit.RetrofitObject
import com.example.myapplication111.Retrofit.adapter.AdapterCoctail
import com.example.myapplication111.Retrofit.data.DrinkDto2
import com.example.myapplication111.databinding.ActivityMainBinding
import com.example.myapplication111.databinding.FragmentHomeBinding
import com.example.myapplication111.ui.home.DetailActivity
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var binding1: FragmentHomeBinding
    private var mAdapter:AdapterCoctail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val search = menu?.findItem(R.id.search_view)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.queryHint = "Search..."
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return true

            }

            override fun onQueryTextChange(p0: String?): Boolean {

                lifecycleScope.launch {
                    val search = p0?.let {
                        RetrofitObject.retrofit.create(InterFaceApi::class.java).searchDrink(
                            it
                        )
                    }

                    val a = binding.navView.findViewById<RecyclerView>(R.id.rv)
//                    a.apply {
//                        layoutManager = LinearLayoutManager(this@MainActivity)
//                        adapter = mAdapter.apply {
//                            search?.drinks?.let { setADapter(it) }
//
//                            mAdapter?.setOnClick {
//
//                                    lifecycleScope.launch {
//
//                                        val set = RetrofitObject.retrofit.create(InterFaceApi::class.java)
//                                            .getCategories(it.strCategory)
//                                        mAdapter = AdapterCoctail()
//                                        binding1.rv.apply {
//                                            layoutManager  = GridLayoutManager(this@MainActivity, 3)
//                                            adapter = mAdapter?.apply {
//                                                setADapter(set.drinks)
//
//                                                setOnClick {
//
//                                                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
//                                                    intent.putExtra("Data", it.idDrink)
//                                                    startActivity(intent)
//
//
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }


                }

                return true

            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setADapter(list:List<DrinkDto2>){
        mAdapter?.setData(list)
    }


}