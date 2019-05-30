package com.example.mealsmvp.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mealsmvp.R
import com.example.mealsmvp.ui.viewholders.DetailViewHolder
import com.example.mealsmvp.vos.Meal

class MealAdapter2(val context: Context)
    : RecyclerView.Adapter<DetailViewHolder>() {

    private var mMeal: List<Meal> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DetailViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.detail, parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mMeal.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bindData(mMeal[position])
    }

    fun setNewData(meals: List<Meal>) {
        mMeal = meals
        notifyDataSetChanged()
    }
}

