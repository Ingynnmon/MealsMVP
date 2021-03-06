package com.example.mealsmvp.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.mealsmvp.vos.Meal
import kotlinx.android.synthetic.main.card.view.iview
import kotlinx.android.synthetic.main.card.view.tview
import kotlinx.android.synthetic.main.detail.view.*

class DetailViewHolder (itemView: View)
    : RecyclerView.ViewHolder(itemView){

    fun bindData(meal: Meal){
        Glide.with(itemView).load(meal.image).into(itemView.iview)
        itemView.tview.text = meal.strMeal
        itemView.instructiondetail.text = meal.instruction
    }
}