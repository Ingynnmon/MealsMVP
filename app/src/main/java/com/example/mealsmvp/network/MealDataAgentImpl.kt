package com.example.mealsmvp.network

import android.content.Intent
import com.example.mealsmvp.events.RestApiEvents
import com.example.mealsmvp.network.responses.LatestMealResponse
import com.example.mealsmvp.ui.utils.AppConstants
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MealDataAgentImpl private constructor() : MealDataAgent{

    private var mealApi: MealApi
    private var str:String?=null

    companion object{

        private var INSTANCE: MealDataAgentImpl?=null
        fun getInstance(): MealDataAgentImpl{
            if(INSTANCE==null){
                INSTANCE = MealDataAgentImpl()
            }
            val i= INSTANCE
            return i!!
        }
    }

    init {

        /*val myIntent:Intent?=null
        if (myIntent!!.hasExtra("myExtra")) {
            str=myIntent!!.getStringExtra("myExtra")
        }*/

        val okHttpClient = OkHttpClient
            .Builder()
            .connectTimeout(60,TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .build()
        mealApi=retrofit.create(MealApi::class.java)
    }
///////Latest Meals/////////////////////
    override fun getLatestMeals() {
        mealApi.getLatestMeals().enqueue(object : Callback<LatestMealResponse> {
            override fun onFailure(call: Call<LatestMealResponse>, t: Throwable) {
                EventBus.getDefault()
                    .post(
                        RestApiEvents.ErrorInvokingAPIEvent(
                            t.localizedMessage
                        )
                    )
            }

            override fun onResponse(call: Call<LatestMealResponse>, response: Response<LatestMealResponse>) {
                val latestMealResponse = response.body()
                if(latestMealResponse != null && latestMealResponse.meals.isNotEmpty()){
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.LatestMealsDataLoadedEvent(
                                latestMealResponse.meals
                            )
                        )
                }else{
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.ErrorInvokingAPIEvent(
                                "No data found"
                            )
                        )
                }
            }

        })
    }
///////Search Meals /////////
    override fun getSearchMeals(searchValue : String) {

        mealApi.getSearchMeals(searchValue).enqueue(object : Callback<LatestMealResponse> {
            override fun onFailure(call: Call<LatestMealResponse>, t: Throwable) {
                EventBus.getDefault()
                    .post(
                        RestApiEvents.ErrorInvokingAPIEvent(
                            t.localizedMessage
                        )
                    )
            }

            override fun onResponse(call: Call<LatestMealResponse>, response: Response<LatestMealResponse>) {
                val latestMealResponse = response.body()
                if (latestMealResponse != null && latestMealResponse.meals.isNotEmpty()) {
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.LatestMealsDataLoadedEvent(
                                latestMealResponse.meals
                            )
                        )
                } else {
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.ErrorInvokingAPIEvent(
                                "No data found"
                            )
                        )
                }
            }
        })
    }
//////Detail Meals //////////
     override fun getDetailMeals(identity : String) {

        mealApi.getDetailMeals(identity).enqueue(object : Callback<LatestMealResponse> {
            override fun onFailure(call: Call<LatestMealResponse>, t: Throwable) {
                EventBus.getDefault()
                    .post(
                        RestApiEvents.ErrorInvokingAPIEvent(
                            t.localizedMessage
                        )
                    )
            }

            override fun onResponse(call: Call<LatestMealResponse>, response: Response<LatestMealResponse>) {
                val latestMealResponse = response.body()
                if (latestMealResponse != null && latestMealResponse.meals.isNotEmpty()) {
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.LatestMealsDataLoadedEvent(
                                latestMealResponse.meals
                            )
                        )
                } else {
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.ErrorInvokingAPIEvent(
                                "No data found"
                            )
                        )
                }
            }

        })
    }
}