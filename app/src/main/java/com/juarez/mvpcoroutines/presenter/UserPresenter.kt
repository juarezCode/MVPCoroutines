package com.juarez.mvpcoroutines.presenter

import android.content.Context
import android.util.Log
import com.juarez.mvpcoroutines.common.RetryService
import com.juarez.mvpcoroutines.common.UsersMVP
import com.juarez.mvpcoroutines.interactor.UserInteractor
import com.juarez.mvpcoroutines.models.User
import kotlinx.coroutines.*

class UserPresenter(private val view: UsersMVP.View, val context: Context) : UsersMVP.Presenter {
    private val interactor = UserInteractor(this)

    override val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
        Log.d("MVP", "exception $throwable")
        view.onError(throwable, RetryService.NONE)
    }

    override val presenterScope =
        CoroutineScope(Dispatchers.Main + SupervisorJob() + exceptionHandler)

    override fun getUsers() {
        presenterScope.launch {
            interactor.getUsers(context)
        }
    }

    override fun onGetUsersSuccess(users: List<User>) {
        view.onGetUsersSuccess(users)
    }

    override fun getUserById(userId: Int) {
        presenterScope.launch {
            interactor.getUserById(userId)
        }
    }

    override fun onGetUserByIdSuccess(user: User) {
        view.onGetUserByIdSuccess(user)
    }

    override fun onError(e: Throwable, retryService: RetryService) {
        view.onError(e, retryService)
    }
}