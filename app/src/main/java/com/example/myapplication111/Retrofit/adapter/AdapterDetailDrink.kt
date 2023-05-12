package com.example.myapplication111.Retrofit.adapter

import android.icu.util.Measure
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication111.R
import com.example.myapplication111.Retrofit.data.Drink
import com.example.myapplication111.Retrofit.data.DrinkDto2

class AdapterDetailDrink (): RecyclerView.Adapter<AdapterDetailDrink.MyViewHolder>() {
    private val array = mutableListOf<Drink>()

    private var actionEdit:((Drink)->Unit)? = null
    class MyViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val strDrink = item.findViewById<TextView>(R.id.strDrink)
        val imageDrink = item.findViewById<ImageView>(R.id.image)
        val strCategory = item.findViewById<TextView>(R.id.strCategory)
        val strGlass = item.findViewById<TextView>(R.id.strGlass)
        val strAccoholic = item.findViewById<TextView>(R.id.strAlcoholic)
        val strInstructions = item.findViewById<TextView>(R.id.strInstruction)
        val strIngredients = item.findViewById<TextView>(R.id.strIngredients)
        val strMeasures = item.findViewById<TextView>(R.id.strMeasures)
        val strPublished = item.findViewById<TextView>(R.id.strPublished)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_list,parent, false))
    }

    override fun getItemCount(): Int {
        return array.size
    }




    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val arrayList = array[position]
        holder.strDrink.text = "Name : " + arrayList.strDrink
        holder.strCategory.text = "Category : " + arrayList.strCategory
        holder.strGlass.text = "Glass : "+arrayList.strGlass
        holder.strAccoholic.text = "Alcoholic : "+arrayList.strAlcoholic
        holder.strPublished.text = "Published : " +arrayList.dateModified

        holder.strInstructions.text = "Instructions : \n" + arrayList.strInstructions + "\n InstructionFR : \n" + arrayList.strInstructionsFR + " \n InstructionsES : \n" + arrayList.strInstructionsES + "\n InstructionDE : \n" + arrayList.strInstructionsDE + "\n InstructionIT : \n" + arrayList.strInstructionsIT

        holder.strIngredients.text = "Ingredients : \n" + arrayList.strIngredient1+"\n" + arrayList.strIngredient2 +"\n"+arrayList.strIngredient3+"\n"+arrayList.strIngredient4

        holder.strMeasures.text = "Measures : \n" + arrayList.strMeasure1+"\n" + arrayList.strMeasure2+"\n" + arrayList.strMeasure3
        Glide.with(holder.imageDrink.context).load(arrayList.strDrinkThumb).into(holder.imageDrink)
    }
    fun setData(data: List<Drink>){
        array.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }




}