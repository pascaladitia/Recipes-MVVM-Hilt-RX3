package com.pascal.recipes.data.repo

import com.pascal.recipes.data.api.ApiResponse
import com.pascal.recipes.data.api.ApiService
import com.pascal.recipes.data.model.ResponseListCategory
import com.pascal.recipes.data.model.ResponseListRecipe
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getRecipes(
        name: String,
        compositeDisposable: CompositeDisposable,
        onResponse: ApiResponse<ResponseListRecipe>
    ): Disposable {
        return apiService.getRecipes(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onResponse.onSuccess(it)
            }, {
                onResponse.onError(it)
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun getSearch(
        name: String,
        compositeDisposable: CompositeDisposable,
        onResponse: ApiResponse<ResponseListRecipe>
    ): Disposable {
        return apiService.getSearch(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onResponse.onSuccess(it)
            }, {
                onResponse.onError(it)
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun getCategory(
        compositeDisposable: CompositeDisposable,
        onResponse: ApiResponse<ResponseListCategory>
    ): Disposable {
        return apiService.getCategory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                  onResponse.onSuccess(it)
            }, {
                onResponse.onError(it)
            }).also {
                compositeDisposable.add(it)
            }
    }
}