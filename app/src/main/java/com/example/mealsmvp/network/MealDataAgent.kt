package com.example.mealsmvp.network

interface MealDataAgent {
    fun getLatestMeals()
    fun getSearchMeals(searchValue : String)
    fun getDetailMeals(identity : String)
}