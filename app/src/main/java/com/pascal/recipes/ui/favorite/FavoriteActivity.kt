package com.pascal.recipes.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pascal.recipes.data.local.model.Favorite
import com.pascal.recipes.data.local.viewModel.ViewmodelLocal
import com.pascal.recipes.databinding.ActivityFavoriteBinding
import com.pascal.recipes.ui.adapter.AdapterFavorite
import com.pascal.recipes.ui.detail.DetailActivity
import com.pascal.recipes.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: ViewmodelLocal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        attachObserve()
    }

    private fun attachObserve() {
        with(viewModel) {
            loadFavorite()

            listFavorite.observe(this@FavoriteActivity, Observer {
                showData(it)
            })

            isLoading.observe(this@FavoriteActivity, Observer {
                binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            })

            isError.observe(this@FavoriteActivity, Observer {
                Log.e("tag error", it.toString())
            })

            viewModel.responseDelete.observe(this@FavoriteActivity, Observer {
                toast("Delete success")
                loadFavorite()
            })
        }
    }

    private fun showData(it: List<Favorite>?) {
        if (it!!.isNotEmpty()) {
            val adapter = AdapterFavorite(it, object : AdapterFavorite.OnClickListener {
                override fun detail(item: Favorite) {
                    val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                    intent.putExtra("data", item)
                    startActivity(intent)
                }

                override fun favorite(item: Favorite) {
                    viewModel.deleteFavorite(item)
                }
            })
            binding.recyclerList.adapter = adapter
        } else {
            toast("Not Found")
        }
    }
}