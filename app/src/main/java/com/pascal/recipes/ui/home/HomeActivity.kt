package com.pascal.recipes.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pascal.recipes.data.local.model.Favorite
import com.pascal.recipes.data.local.viewModel.ViewmodelLocal
import com.pascal.recipes.data.remote.model.MealsItem
import com.pascal.recipes.data.remote.model.ResponseListRecipe
import com.pascal.recipes.databinding.ActivityHomeBinding
import com.pascal.recipes.ui.adapter.AdapterRecipes
import com.pascal.recipes.ui.detail.DetailActivity
import com.pascal.recipes.ui.favorite.FavoriteActivity
import com.pascal.recipes.ui.recipes.ListRecipeActivity
import com.pascal.recipes.utils.getRandomQuery
import com.pascal.recipes.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val viewModelLocal: ViewmodelLocal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initView()
        attachObserve()
    }

    private fun initView() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val intent = Intent(this@HomeActivity, ListRecipeActivity::class.java)
                intent.putExtra("search", query)
                startActivity(intent)

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })

        binding.seafood.setOnClickListener {
            val intent = Intent(this@HomeActivity, ListRecipeActivity::class.java)
            intent.putExtra("data", "seafood")
            startActivity(intent)
        }
        binding.daging.setOnClickListener {
            val intent = Intent(this@HomeActivity, ListRecipeActivity::class.java)
            intent.putExtra("data", "meat")
            startActivity(intent)
        }
        binding.ayam.setOnClickListener {
            val intent = Intent(this@HomeActivity, ListRecipeActivity::class.java)
            intent.putExtra("data", "chicken")
            startActivity(intent)
        }
        binding.sayur.setOnClickListener {
            val intent = Intent(this@HomeActivity, ListRecipeActivity::class.java)
            intent.putExtra("data", "vegetable")
            startActivity(intent)
        }
        binding.seeFav.setOnClickListener {
            val intent = Intent(this@HomeActivity, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun attachObserve() {
        Log.e("tag random", getRandomQuery())
        with(viewModel) {
            loadRecipes(getRandomQuery())

            listRecipes.observe(this@HomeActivity, Observer {
                showData(it)
            })

            isLoading.observe(this@HomeActivity, Observer {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            })

            isError.observe(this@HomeActivity, Observer {
                Log.e("tag error", it.toString())
            })
        }

        with(viewModelLocal) {
            loadFavorite()

            responseInsert.observe(this@HomeActivity, Observer {
                toast("Insert Success")
            })
        }
    }

    private fun showData(it: ResponseListRecipe?) {
        if (it?.meals!!.isNotEmpty()) {
            val adapter = AdapterRecipes(it?.meals, object : AdapterRecipes.OnClickListener {
                override fun detail(item: MealsItem) {
                    val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                    intent.putExtra("data", item)
                    startActivity(intent)
                }

                override fun favorite(item: MealsItem) {
                    viewModelLocal.insertFavorite(
                        Favorite(
                            item.idMeal?.toInt(),
                            item.strMeal,
                            item.strCategory,
                            item.strMealThumb
                        )
                    )
                }
            })
            binding.recyclerList.adapter = adapter
        } else {
            toast("Not Found")
        }
    }
}