package com.example.mealsmvp.events

import com.example.mealsmvp.vos.Meal

object RestApiEvents {
    class ErrorInvokingAPIEvent(val message: String)
    class LatestMealsDataLoadedEvent(val meals: List<Meal>)
    class SearchMealsDataLoadedEvent(val meals: List<Meal>)
}