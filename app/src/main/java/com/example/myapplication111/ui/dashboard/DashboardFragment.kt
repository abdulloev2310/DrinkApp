package com.example.myapplication111.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication111.Retrofit.adapter.AdapterCoctail
import com.example.myapplication111.Retrofit.data.DrinkDto2
import com.example.myapplication111.Room.AppDatabase
import com.example.myapplication111.databinding.FragmentDashboardBinding
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {


    private var mAdapter:AdapterCoctail? = null

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        lifecycleScope.launch {

            val drinkDb = AppDatabase(requireContext()).getTodoDao().getAllDrinks()

            mAdapter = AdapterCoctail()

            binding.rvDbHistory.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = mAdapter.apply {
                    setAdapter(drinkDb)
            }

            }

        }

        return root
    }

    fun setAdapter(list: List<DrinkDto2>) {
        mAdapter?.setData(list)
    }

}