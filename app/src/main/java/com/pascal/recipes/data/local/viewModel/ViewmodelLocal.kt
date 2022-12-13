package com.pascal.recipes.data.local.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.recipes.data.local.model.Favorite
import com.pascal.recipes.data.local.repo.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ViewmodelLocal @Inject constructor(
    private val localRepository: LocalRepository
): ViewModel() {

    private val responseData = MutableLiveData<List<Favorite>>()
    val listFavorite: LiveData<List<Favorite>> = responseData
    var responseInsert = MutableLiveData<Unit>()
    var responseUpdate = MutableLiveData<Unit>()
    var responseDelete = MutableLiveData<Unit>()
    private val compositeDisposable = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun loadFavorite() {
        isLoading.value = true
        localRepository.getFavorite(compositeDisposable, {
            isLoading.value = false
           responseData.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    fun insertFavorite(fav: Favorite) {
        isLoading.value = true
        localRepository.insertFavorite(fav, compositeDisposable, {
            responseInsert.value = it
        }, {
            isError.value = it
        })
    }

    fun updateFavorite(fav: Favorite) {
        isLoading.value = true
        localRepository.updateFavorite(fav, compositeDisposable, {
            responseUpdate.value = it
        }, {
            isError.value = it
        })
    }

    fun deleteFavorite(fav: Favorite) {
        isLoading.value = true
        localRepository.deleteFavorite(fav, compositeDisposable, {
            responseDelete.value = it
        }, {
            isError.value = it
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}