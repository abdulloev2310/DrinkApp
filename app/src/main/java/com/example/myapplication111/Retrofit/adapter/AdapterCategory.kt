package com.example.myapplication111.Retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication111.R
import com.example.myapplication111.Retrofit.data.CategoryDto2
import com.example.myapplication111.Retrofit.data.DrinkDto2

class AdapterCategory : RecyclerView.Adapter<AdapterCategory.MyViewHolder>() {
    private val array = mutableListOf<CategoryDto2>()

    private var actionEdit:((CategoryDto2)->Unit)? = null
    class MyViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val strCategory = item.findViewById<TextView>(R.id.nameCategory)
        val eee = item.findViewById<ConstraintLayout>(R.id.qqq)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_list,parent, false))
    }

    override fun getItemCount(): Int {
        return array.size
    }




    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val arrayList = array[position]
        holder.strCategory.text = arrayList.strCategory
        holder.eee.setOnClickListener { actionEdit?.invoke(arrayList) }

    }
    fun setData(data: List<CategoryDto2>){
        array.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }


    fun setOnClick(callback:(CategoryDto2) -> Unit){
        this.actionEdit=callback
    }
}