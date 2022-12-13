package com.pascal.recipes.data.remote.repo

import com.pascal.recipes.data.remote.api.ApiService
import com.pascal.recipes.data.remote.model.ResponseListCategory
import com.pascal.recipes.data.remote.model.ResponseListRecipe
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
        onResponse: (ResponseListRecipe) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return apiService.getRecipes(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onResponse(it)
            }, {
                onError(it)
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun getSearch(
        name: String,
        compositeDisposable: CompositeDisposable,
        onResponse: (ResponseListRecipe) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return apiService.getSearch(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onResponse(it)
            }, {
                onError(it)
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun getCategory(
        compositeDisposable: CompositeDisposable,
        onResponse: (ResponseListCategory) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return apiService.getCategory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onResponse(it)
            }, {
                onError(it)
            }).also {
                compositeDisposable.add(it)
            }
    }
}