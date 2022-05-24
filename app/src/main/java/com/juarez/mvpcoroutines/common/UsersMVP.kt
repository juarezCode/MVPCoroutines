package com.juarez.mvpcoroutines.common

import android.content.Context
import com.juarez.mvpcoroutines.models.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

interface UsersMVP {

    interface View {
        fun onGetUsersSuccess(users: List<User>)
        fun onGetUserByIdSuccess(user: User)
        fun onError(e: Throwable, retryService: RetryService)
    }

    interface Presenter {
        val exceptionHandler: CoroutineExceptionHandler
        val presenterScope: CoroutineScope
        fun getUsers()
        fun onGetUsersSuccess(users: List<User>)
        fun getUserById(userId: Int)
        fun onGetUserByIdSuccess(user: User)
        fun onCleared() = presenterScope.cancel()
        fun onError(e: Throwable, retryService: RetryService)
    }

    interface Interactor {
        suspend fun getUsers(context: Context)
        suspend fun getUserById(userId: Int)
    }
}