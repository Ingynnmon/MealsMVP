package com.example.mealsmvp.mvp.presenters

import com.example.mealsmvp.events.RestApiEvents
import com.example.mealsmvp.mvp.models.MealModel
import com.example.mealsmvp.mvp.views.LatestMealView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class LatestMealsPresenter constructor(val mView: LatestMealView) : BasePresenter() {

    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    fun startLoadingLatestMeals() {
        mView.showLoading()
        MealModel.getInstance().getLatestMeals()
    }

    override fun onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onLatestMealsLoaded(event: RestApiEvents.LatestMealsDataLoadedEvent) {
        mView.dismissLoading()
        mView.displayMeals(event.meals)
    }

    @Subscribe
    fun onError(event: RestApiEvents.ErrorInvokingAPIEvent) {
        mView.dismissLoading()
        mView.showPrompt(event.message)
    }
}