package com.example.mealsmvp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.EditText
import android.widget.Toast
import com.example.mealsmvp.R
import com.example.mealsmvp.mvp.presenters.LatestMealsPresenter
import com.example.mealsmvp.mvp.views.LatestMealView
import com.example.mealsmvp.ui.adapters.ItemClickListener
import com.example.mealsmvp.ui.adapters.MealAdapter
import com.example.mealsmvp.vos.Meal
import kotlinx.android.synthetic.main.search.*

class SearchActivity : AppCompatActivity(), LatestMealView {


    private lateinit var mAdapter: MealAdapter
    private lateinit var mPresenter: LatestMealsPresenter
    private lateinit var name: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        //setSupportActionBar(toolbar)

        name=findViewById(R.id.mealName)
        mPresenter = LatestMealsPresenter(this)
        mPresenter.startLoadingLatestMeals()

        mAdapter = MealAdapter(this,object : ItemClickListener{
            override fun onItemClicked(id: String) {
                //To DetailActivity with id Data
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }

        })
        recyclerViewSearch.adapter = mAdapter
        recyclerViewSearch.setHasFixedSize(true)
        recyclerViewSearch.layoutManager = GridLayoutManager(this,2)

        swipeRefresh2.setOnRefreshListener {
            val searchValue = name.text.toString()
            mPresenter.startLoadingSearchMeals(searchValue)
        }

        searchMeal.setOnClickListener{

            val searchValue = name.text.toString()
            mPresenter.startLoadingSearchMeals(searchValue)
        }

        mPresenter.startLoadingDetailMeals(identity = "id")
    }


    override fun displayMeals(meals: List<Meal>) {
        mAdapter.setNewData(meals)
    }

    override fun showPrompt(message: String) {
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        if (!swipeRefresh2.isRefreshing) {
            swipeRefresh2.isRefreshing = true
        }
    }

    override fun dismissLoading() {
        if (swipeRefresh2.isRefreshing) {
            swipeRefresh2.isRefreshing = false
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