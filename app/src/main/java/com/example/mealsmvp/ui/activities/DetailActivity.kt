package com.example.mealsmvp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.GridLayout
import android.widget.Toast
import com.example.mealsmvp.R
import com.example.mealsmvp.mvp.presenters.DetailMealsPresenter
import com.example.mealsmvp.mvp.presenters.LatestMealsPresenter
import com.example.mealsmvp.mvp.views.DetailMealView
import com.example.mealsmvp.ui.adapters.MealAdapter2
import com.example.mealsmvp.vos.Meal
import kotlinx.android.synthetic.main.detail_main.*

class DetailActivity : AppCompatActivity(), DetailMealView {


    private lateinit var mAdapter: MealAdapter2
    private lateinit var mPresenter: DetailMealsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_main)
        //setSupportActionBar(toolbar)


        val bundle :Bundle?= intent.extras
        val identity = bundle!!.getString("id")
        val text : String = intent.getStringExtra("id")

        mPresenter = DetailMealsPresenter(this)
        mPresenter.startLoadingDetailMeals(identity)

        mAdapter = MealAdapter2(this)
        recyclerViewDetail.adapter = mAdapter
        recyclerViewDetail.setHasFixedSize(true)
        recyclerViewDetail.layoutManager = GridLayoutManager(this,1, GridLayout.VERTICAL,false)

        swipeRefresh3.setOnRefreshListener {
            mPresenter.startLoadingDetailMeals(identity)
        }
    }


    override fun displayMeals(meals: List<Meal>) {
        mAdapter.setNewData(meals)
    }

    override fun showPrompt(message: String) {
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        if (swipeRefresh3.isRefreshing) {
            swipeRefresh3.isRefreshing = true
        }
    }

    override fun dismissLoading() {
        if (swipeRefresh3.isRefreshing) {
            swipeRefresh3.isRefreshing = false
        }
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        val bundle :Bundle?= intent.extras
        val identity = bundle!!.getString("id")
        val text : String = intent.getStringExtra("id")
        mPresenter.startLoadingDetailMeals(identity)
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }
}
