package com.example.mealsmvp.mvp.models

import com.example.mealsmvp.network.MealDataAgentImpl

class MealModel {
    companion object {
        private var INSTANCE: MealModel? = null
        fun getInstance(): MealModel {
            if (INSTANCE == null) {
                INSTANCE = MealModel()
            }
            val i = INSTANCE
            return i!!
        }
    }

    fun getLatestMeals() {
        MealDataAgentImpl.getInstance().getLatestMeals()
    }

    fun getSearchMeals() {
        MealDataAgentImpl.getInstance().getSearchMeals()
    }
}