package com.pascal.recipes.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.recipes.data.api.ApiResponse
import com.pascal.recipes.data.model.ResponseListCategory
import com.pascal.recipes.data.repo.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
): ViewModel() {

    private val responseData = MutableLiveData<ResponseListCategory?>()
    val listCategory: LiveData<ResponseListCategory?> = responseData
    val isLoading = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

    fun loadCategory() {
        showLoading(true)
        remoteRepository.getCategory(compositeDisposable, object :
            ApiResponse<ResponseListCategory> {
            override fun onSuccess(result: ResponseListCategory?) {
                showLoading(false)
                responseData.value = result
            }

            override fun onError(t: Throwable) {
                showLoading(false)
                t.printStackTrace()
            }

        })
    }

    private fun showLoading(isVisible: Boolean) {
        isLoading.value = isVisible
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}