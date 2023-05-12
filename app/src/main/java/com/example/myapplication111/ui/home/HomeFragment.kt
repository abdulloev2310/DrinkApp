package com.example.myapplication111.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication111.R
import com.example.myapplication111.Retrofit.InterFaceApi
import com.example.myapplication111.Retrofit.RetrofitObject
import com.example.myapplication111.Retrofit.adapter.AdapterCategory
import com.example.myapplication111.Retrofit.adapter.AdapterCoctail
import com.example.myapplication111.Retrofit.data.CategoryDto2
import com.example.myapplication111.Retrofit.data.DrinkDto
import com.example.myapplication111.Retrofit.data.DrinkDto2
import com.example.myapplication111.Room.AppDatabase
import com.example.myapplication111.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private var mAdapter:AdapterCoctail? = null

    private var mAdapterCategory:AdapterCategory? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.apply {

            lifecycleScope.launch  {

                val drink = RetrofitObject.retrofit.create(InterFaceApi::class.java).getCategories("Ordinary Drink")
                Log.e("Shot", drink.toString())



                mAdapter = AdapterCoctail()
                rv.apply {

                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = mAdapter?.apply {

                        setAdapterDrinks(drink.drinks)
                     setOnClick {
                         lifecycleScope.launch {

                             val todo = DrinkDto2(
                                 it.strDrink, it.strDrinkThumb, it.idDrink
                             )

                             AppDatabase(requireContext()).getTodoDao().addDrinkToFragment(todo)

                             val intent = Intent(requireContext(), DetailActivity::class.java)
                             intent.putExtra("Data", it.idDrink)
                             startActivity(intent)
                         }
                        }

                                }
                            }

                val category = RetrofitObject.retrofit.create(InterFaceApi::class.java).getCategory()
                mAdapterCategory = AdapterCategory()
                rvCategory.apply {
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = mAdapterCategory.apply {
                        setAdapterCategory(category.drinks)
                        mAdapterCategory?.setOnClick {


                            lifecycleScope.launch {

                                val set = RetrofitObject.retrofit.create(InterFaceApi::class.java)
                                    .getCategories(it.strCategory)
                                mAdapter = AdapterCoctail()
                                binding.rv.apply {
                                    layoutManager  = GridLayoutManager(requireContext(), 2)
                                    adapter = mAdapter?.apply {
                                        setAdapterDrinks(set.drinks)
                                        textCategory.text = it.strCategory

                                        setOnClick {

                                            lifecycleScope.launch {

                                                val todo = DrinkDto2(
                                                    it.strDrink, it.strDrinkThumb, it.idDrink
                                                )

                                                AppDatabase(requireContext()).getTodoDao().addDrinkToFragment(todo)

                                                val intent = Intent(requireContext(), DetailActivity::class.java)
                                                intent.putExtra("Data", it.idDrink)
                                                startActivity(intent)
                                            }

                                        }

                                    }
                                }
                            }
                        }
                    }
                }


                    }


        }




        return root
    }

    fun setAdapterDrinks(list: List<DrinkDto2>) {
        mAdapter?.setData(list)
    }
    fun setAdapterCategory(list: List<CategoryDto2>) {
        mAdapterCategory?.setData(list)
    }


}