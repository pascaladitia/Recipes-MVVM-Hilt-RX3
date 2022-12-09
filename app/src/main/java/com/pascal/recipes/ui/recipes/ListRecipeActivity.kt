package com.pascal.recipes.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pascal.recipes.data.model.MealsItem
import com.pascal.recipes.data.model.ResponseListRecipe
import com.pascal.recipes.databinding.ActivityListRecipeBinding
import com.pascal.recipes.ui.adapter.AdapterRecipes
import com.pascal.recipes.ui.detail.DetailActivity
import com.pascal.recipes.ui.home.HomeViewModel
import com.pascal.recipes.utils.getRandomQuery
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListRecipeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var query: String? = "a"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        attachObserve()
    }

    private fun attachObserve() {
        val item = intent.getStringExtra("data")
        val search = intent.getStringExtra("search")

        if (item != null) {
            query = item
        } else if (search != null) {
            query = search
        } else {
            query = getRandomQuery()
        }

        with(viewModel) {
            loadSearh(query.toString())

            listRecipes.observe(this@ListRecipeActivity, Observer {
                showData(it)
            })

            isLoading.observe(this@ListRecipeActivity, Observer {
                binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            })

            isError.observe(this@ListRecipeActivity, Observer {
                Log.e("tag error", it)
            })
        }
    }

    private fun showData(it: ResponseListRecipe?) {
        if (it?.meals!!.isNotEmpty()) {
            val adapter = AdapterRecipes(it?.meals, object : AdapterRecipes.OnClickListener {
                override fun detail(item: MealsItem) {
                    val intent = Intent(this@ListRecipeActivity, DetailActivity::class.java)
                    intent.putExtra("data", item)
                    startActivity(intent)
                }

                override fun favorite(item: MealsItem) {

                }
            })
            binding.recyclerList.adapter = adapter
        } else {
            attachObserve()
        }
    }
}