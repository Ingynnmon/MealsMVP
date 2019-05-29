package com.example.mealsmvp.mvp.views

import com.example.mealsmvp.vos.Meal

interface SearchMealView {
    fun displayMeals(meals: List<Meal>)
    fun showPrompt(message: String)
    fun showLoading()
    fun dismissLoading()
}