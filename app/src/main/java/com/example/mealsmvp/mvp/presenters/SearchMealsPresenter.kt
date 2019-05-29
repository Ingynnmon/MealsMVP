package com.example.mealsmvp.mvp.presenters

import com.example.mealsmvp.events.RestApiEvents
import com.example.mealsmvp.mvp.models.MealModel
import com.example.mealsmvp.mvp.views.LatestMealView
import com.example.mealsmvp.mvp.views.SearchMealView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class SearchMealsPresenter constructor(val mView: SearchMealView) : BasePresenter() {

    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    fun startLoadingSearchMeals() {
        mView.showLoading()
        MealModel.getInstance().getSearchMeals()
    }

    override fun onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onSearchMealsLoaded(event: RestApiEvents.SearchMealsDataLoadedEvent) {
        mView.dismissLoading()
        mView.displayMeals(event.meals)
    }

    @Subscribe
    fun onError(event: RestApiEvents.ErrorInvokingAPIEvent) {
        mView.dismissLoading()
        mView.showPrompt(event.message)
    }
}