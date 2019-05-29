package com.example.mealsmvp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.EditText
import android.widget.Toast
import com.example.mealsmvp.R
import com.example.mealsmvp.mvp.presenters.SearchMealsPresenter
import com.example.mealsmvp.mvp.views.SearchMealView
import com.example.mealsmvp.ui.adapters.MealAdapter
import com.example.mealsmvp.vos.Meal
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.search.*

class SearchActivity : AppCompatActivity(),SearchMealView {


    private lateinit var mAdapter: MealAdapter
    private lateinit var mPresenter: SearchMealsPresenter
    public lateinit var name: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        //setSupportActionBar(toolbar)

        name=findViewById(R.id.mealName)
        mPresenter = SearchMealsPresenter(this)
        mPresenter.startLoadingSearchMeals()

        mAdapter = MealAdapter(this)
        recyclerViewSearch.adapter = mAdapter
        recyclerViewSearch.setHasFixedSize(true)
        recyclerViewSearch.layoutManager = GridLayoutManager(this,2)

        swipeRefresh2.setOnRefreshListener {
            mPresenter.startLoadingSearchMeals()
        }

        searchMeal.setOnClickListener{

            /*val i = Intent(this,MealDataAgentImpl::class.java)
            i.putExtra("myExtra", name.text.toString())
            startActivity(i)*/

            mPresenter.startLoadingSearchMeals()
        }
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