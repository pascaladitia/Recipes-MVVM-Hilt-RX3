package com.pascal.recipes.data.local.repo

import com.pascal.recipes.data.local.db.FavoriteDao
import com.pascal.recipes.data.local.model.Favorite
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LocalRepository@Inject constructor(
    private val favDao: FavoriteDao
) {
    fun getFavorite(
        compositeDisposable: CompositeDisposable,
        response: (List<Favorite>) -> Unit,
        error: (Throwable) -> Unit
    ): Disposable {
        return Observable.fromCallable { favDao.getAllFav() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response(it)
            }, {
                error(it)
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun insertFavorite(
        fav: Favorite,
        compositeDisposable: CompositeDisposable,
        response: (Unit) -> Unit, error: (Throwable) -> Unit
    ): Disposable {
        return Observable.fromCallable { favDao.insert(fav) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response(it)
            }, {
                error(it)
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun updateFavorite(
        fav: Favorite,
        compositeDisposable: CompositeDisposable,
        response: (Unit) -> Unit, error: (Throwable) -> Unit
    ): Disposable {
        return Observable.fromCallable { favDao.update(fav) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response(it)
            }, {
                error(it)
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun deleteFavorite(
        fav: Favorite,
        compositeDisposable: CompositeDisposable,
        response: (Unit) -> Unit, error: (Throwable) -> Unit
    ): Disposable {
        return Observable.fromCallable { favDao.delete(fav) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response(it)
            }, {
                error(it)
            }).also {
                compositeDisposable.add(it)
            }
    }
}