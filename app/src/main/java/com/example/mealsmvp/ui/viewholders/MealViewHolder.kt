package com.example.mealsmvp.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.mealsmvp.ui.adapters.ItemClickListener
import com.example.mealsmvp.vos.Meal
import kotlinx.android.synthetic.main.card.view.*

class MealViewHolder (itemView: View,private val itemClickListener: ItemClickListener)
    : RecyclerView.ViewHolder(itemView){

    fun bindData(meal:Meal){
        Glide.with(itemView).load(meal.image).into(itemView.iview)
        itemView.tview.text = meal.strMeal
        itemView.setOnClickListener {
            itemClickListener.onItemClicked(meal.idMeal!!)
        }
    }
}