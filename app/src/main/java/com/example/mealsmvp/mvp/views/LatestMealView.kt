package com.example.mealsmvp.mvp.views

import com.example.mealsmvp.vos.Meal

interface LatestMealView {
    fun displayMeals(meals: List<Meal>)
    fun showPrompt(message: String)
    fun showLoading()
    fun dismissLoading()
}