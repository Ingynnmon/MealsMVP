package com.example.mealsmvp.vos

import com.google.gson.annotations.SerializedName

class Meal{
    @SerializedName("idMeal")
    val idMeal: String?=null

    @SerializedName("strMeal")
    val strMeal:String?=null

    @SerializedName("strMealThumb")
    val image:String?=null

    @SerializedName("strInstructions")
    val instruction:String?=null

    @SerializedName("strTags")
    val tag:String?=null
}