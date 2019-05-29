package com.example.mealsmvp.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.example.mealsmvp.R
import com.example.mealsmvp.mvp.presenters.LatestMealsPresenter
import com.example.mealsmvp.mvp.views.LatestMealView
import com.example.mealsmvp.ui.adapters.MealAdapter
import com.example.mealsmvp.vos.Meal
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), LatestMealView {

    private lateinit var mPresenter: LatestMealsPresenter

    private lateinit var mAdapter: MealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        mPresenter = LatestMealsPresenter(this)
        mPresenter.startLoadingLatestMeals()

        mAdapter = MealAdapter(this)
        recyclerViewMeal.adapter = mAdapter
        recyclerViewMeal.setHasFixedSize(true)
        recyclerViewMeal.layoutManager = GridLayoutManager(this,2)

        swipeRefresh.setOnRefreshListener {
            mPresenter.startLoadingLatestMeals()
        }

        searchBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

    }

    override fun displayMeals(meals: List<Meal>) {
        mAdapter.setNewData(meals)
    }

    override fun showPrompt(message: String) {
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        if (!swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = true
        }
    }

    override fun dismissLoading() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }
}
