package com.pascal.recipes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.recipes.data.remote.model.ResponseListRecipe
import com.pascal.recipes.data.remote.repo.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val responseData = MutableLiveData<ResponseListRecipe?>()
    val listRecipes: LiveData<ResponseListRecipe?> = responseData
    private val compositeDisposable = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun loadRecipes(name: String) {
        isLoading.value = true
        remoteRepository.getRecipes(name, compositeDisposable, {

            isLoading.value = false
            responseData.value = it

        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    fun loadSearh(name: String) {
        isLoading.value = true
        remoteRepository.getSearch(name, compositeDisposable, {

            isLoading.value = false
            responseData.value = it

        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}