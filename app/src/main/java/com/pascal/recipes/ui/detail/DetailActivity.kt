package com.pascal.recipes.ui.detail

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pascal.recipes.R
import com.pascal.recipes.data.remote.model.MealsItem
import com.pascal.recipes.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var item: MealsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initView()
        showData()
    }

    private fun initView() {

        binding.back.setOnClickListener {
            onBackPressed()
            finish()
        }

        binding.favorite.setOnClickListener {
//            Toast("Coming soon...")
        }

        binding.tapStep.setOnClickListener {
            val layout = binding.constrain1
            val mSlideLeft = Slide()
            mSlideLeft.slideEdge = Gravity.BOTTOM
            TransitionManager.beginDelayedTransition(layout, mSlideLeft)
            binding.layoutStep.visibility = View.VISIBLE
        }

        binding.tapStepDown.setOnClickListener {
            val layout = binding.constrain1
            val mSlideLeft = Slide()
            mSlideLeft.slideEdge = Gravity.BOTTOM
            TransitionManager.beginDelayedTransition(layout, mSlideLeft)
            binding.layoutStep.visibility = View.INVISIBLE
        }
    }

    private fun showData() {
        item = intent.getParcelableExtra("data")

        binding.name.setText(item?.strMeal)
        binding.desc.setText(item?.strCategory)
        Glide.with(binding.root)
            .load(item?.strMealThumb)
            .apply(
                RequestOptions()
                    .override(700, 700)
                    .placeholder(R.drawable.progress_animation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
            )
            .into(binding.imageView4)

        val bahan  = """
            - ${item?.strIngredient1}
            - ${item?.strIngredient1}
            - ${item?.strIngredient2}
            - ${item?.strIngredient3}
            - ${item?.strIngredient4}
            - ${item?.strIngredient5}
            - ${item?.strIngredient6}
            - ${item?.strIngredient7}
        """.trimIndent()
        binding.txtBahan.setText(bahan)

        //step
        binding.txtStep.setText(item?.strInstructions)
    }
}