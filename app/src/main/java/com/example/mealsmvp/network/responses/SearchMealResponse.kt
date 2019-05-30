package com.example.mealsmvp.network.responses

import com.example.mealsmvp.vos.Meal
import com.google.gson.annotations.SerializedName

class SearchMealResponse (@SerializedName("meals") var meals: List<Meal> = ArrayList())