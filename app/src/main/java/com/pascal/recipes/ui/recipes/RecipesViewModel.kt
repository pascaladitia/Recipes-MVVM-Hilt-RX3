package com.pascal.recipes.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.recipes.data.remote.model.ResponseListCategory
import com.pascal.recipes.data.remote.repo.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
): ViewModel() {

    private val responseData = MutableLiveData<ResponseListCategory?>()
    val listCategory: LiveData<ResponseListCategory?> = responseData
    private val compositeDisposable = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun loadCategory() {
        isLoading.value = true
        remoteRepository.getCategory(compositeDisposable, {

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