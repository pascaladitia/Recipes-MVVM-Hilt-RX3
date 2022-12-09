package com.pascal.recipes.data.api

interface ApiResponse<Type> {

    fun onSuccess(result: Type?)
    fun onError(t: Throwable)
}
