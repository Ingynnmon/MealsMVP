package com.example.mealsmvp.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import com.example.mealsmvp.R
import com.example.mealsmvp.ui.viewholders.MealViewHolder
import com.example.mealsmvp.vos.Meal

class MealAdapter(val context: Context): RecyclerView.Adapter<MealViewHolder>(){

    private var mMeal: List<Meal> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MealViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.card, parent, false)
        return MealViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mMeal.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bindData(mMeal[position])
    }

    fun setNewData(meals : List<Meal>){
        mMeal = meals
        notifyDataSetChanged()
    }


}