package com.pascal.recipes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.recipes.data.api.ApiResponse
import com.pascal.recipes.data.model.ResponseListRecipe
import com.pascal.recipes.data.repo.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
): ViewModel() {

    private val responseData = MutableLiveData<ResponseListRecipe?>()
    val listRecipes: LiveData<ResponseListRecipe?> = responseData
    private val compositeDisposable = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<String>()

    fun loadRecipes(name: String) {
        showLoading(true)
        remoteRepository.getRecipes(name, compositeDisposable, object : ApiResponse<ResponseListRecipe> {
            override fun onSuccess(result: ResponseListRecipe?) {
                showLoading(false)
                responseData.value = result
            }

            override fun onError(t: Throwable) {
                showLoading(false)
                showError(t.toString())
            }

        })
    }

    fun loadSearh(name: String) {
        showLoading(true)
        remoteRepository.getSearch(name, compositeDisposable, object : ApiResponse<ResponseListRecipe> {
            override fun onSuccess(result: ResponseListRecipe?) {
                showLoading(false)
                responseData.value = result
            }

            override fun onError(t: Throwable) {
                showLoading(false)
                showError(t.toString())
            }

        })
    }

    private fun showLoading(isVisible: Boolean) {
        isLoading.value = isVisible
    }

    private fun showError(msg: String) {
        isError.value = msg
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}