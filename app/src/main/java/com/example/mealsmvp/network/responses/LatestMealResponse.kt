package com.example.mealsmvp.network.responses

import com.example.mealsmvp.vos.Meal
import com.google.gson.annotations.SerializedName

class LatestMealResponse (@SerializedName("meals") var meals: List<Meal> = ArrayList())