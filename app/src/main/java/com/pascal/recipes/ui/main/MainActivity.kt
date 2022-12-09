package com.pascal.recipes.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pascal.recipes.R
import com.pascal.recipes.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Handler().postDelayed( {
            startActivity(Intent(this, LandingActivity::class.java))
            finish()
        }, 2000
        )
    }
}