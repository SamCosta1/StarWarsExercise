package com.example.samcosta.starwarsexercise.engine.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by samcosta on 07/01/2019.
 *
 *
 * Disclaimer: I have not written this file from scratch for this exercise, it's modified from a version
 * I've written for another Kotlin project
 */

typealias ApiCallCompletion<T> = (ApiResponse<T>) -> Unit

private fun parseError(response: Response<*>?): CallFailure.ApiError? {
    val error = response?.errorBody()?.string() ?: return null

    return try {
        CallFailure.ApiError(response.code(), error)
    } catch (ignored: Throwable) {
        null
    }
}

fun <T>Call<T>.enqueue(completion: ApiCallCompletion<T>) {
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>?, t: Throwable?) {
            completion(ApiResponse.failure(t))
        }

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            val isSuccess = response?.isSuccessful ?: false

            val result = if (isSuccess){
                ApiResponse.success(response?.body())
            } else {
                ApiResponse.error(parseError(response))
            }

            completion(result)
        }
    })
}

data class ApiResponse<T>(val isSuccess: Boolean, val body: T?, val errorBody: CallFailure?) {

    fun <S>convert(): ApiResponse<S> {
        if (this.isSuccess) {
            throw IllegalArgumentException("Can only convert error responses")
        }

        return ApiResponse(false, null, this.errorBody)
    }

    companion object {
        fun <T> failure(throwable: Throwable?): ApiResponse<T> = ApiResponse(false, null, CallFailure.ApiFailure(throwable))
        fun <T> success(body: T?): ApiResponse<T> = ApiResponse(true, body, null)
        fun <T> error(error: CallFailure.ApiError?): ApiResponse<T> = ApiResponse(false, null, error)
    }
}

sealed class CallFailure {
    data class ApiError(val errorCode: Int, val rawMessage: String): CallFailure()
    data class ApiFailure(val throwable: Throwable?): CallFailure()
}
