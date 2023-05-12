package com.example.myapplication111.Retrofit.adapter

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication111.R
import com.example.myapplication111.Retrofit.data.DrinkDto2
import java.util.Collections.addAll

class AdapterCoctail(): RecyclerView.Adapter<AdapterCoctail.MyViewHolder>() {
    private val array = mutableListOf<DrinkDto2>()

    private var actionEdit:((DrinkDto2)->Unit)? = null
    class MyViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val strDrink = item.findViewById<TextView>(R.id.name)
        val imageDrink = item.findViewById<ImageView>(R.id.img)
        val detailing = item.findViewById<CardView>(R.id.detailing)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listitem,parent, false))
    }

    override fun getItemCount(): Int {
        return array.size
    }




    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val arrayList = array[position]
        holder.strDrink.text = arrayList.strDrink
        holder.detailing.setOnClickListener{actionEdit?.invoke(arrayList)}
        Glide.with(holder.imageDrink.context).load(arrayList.strDrinkThumb).into(holder.imageDrink)
    }
    fun setData(data: List<DrinkDto2>){
        array.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }


    fun setOnClick(callback:(DrinkDto2) -> Unit){
        this.actionEdit=callback
    }
}