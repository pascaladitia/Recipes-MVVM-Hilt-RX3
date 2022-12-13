package com.pascal.recipes.data.remote.api

import com.pascal.recipes.data.remote.model.ResponseListCategory
import com.pascal.recipes.data.remote.model.ResponseListRecipe
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    fun getRecipes(
        @Query("f") name: String
    ): Single<ResponseListRecipe>

    @GET("search.php")
    fun getSearch(
        @Query("s") name: String
    ): Single<ResponseListRecipe>

    @GET("categories.php")
    fun getCategory(): Single<ResponseListCategory>
}