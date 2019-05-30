package com.example.mealsmvp.mvp.presenters

import com.example.mealsmvp.events.RestApiEvents
import com.example.mealsmvp.mvp.models.MealModel
import com.example.mealsmvp.mvp.views.DetailMealView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class DetailMealsPresenter constructor(val mView: DetailMealView) : BasePresenter() {

    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    fun startLoadingDetailMeals(identity : String) {
        mView.showLoading()
        MealModel.getInstance().getDetailMeals(identity)
    }

    override fun onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onDetailMealsLoaded(event: RestApiEvents.DetailMealsDataLoadedEvent) {
        mView.dismissLoading()
        mView.displayMeals(event.meals)
    }

    @Subscribe
    fun onError(event: RestApiEvents.ErrorInvokingAPIEvent) {
        mView.dismissLoading()
        mView.showPrompt(event.message)
    }
}