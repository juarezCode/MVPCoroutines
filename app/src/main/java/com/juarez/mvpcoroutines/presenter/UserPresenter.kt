package com.juarez.mvpcoroutines.presenter

import android.content.Context
import android.util.Log
import com.juarez.mvpcoroutines.common.RetryService
import com.juarez.mvpcoroutines.common.UsersMVP
import com.juarez.mvpcoroutines.interactor.UserInteractor
import com.juarez.mvpcoroutines.models.Album
import com.juarez.mvpcoroutines.models.User
import kotlinx.coroutines.*

class UserPresenter(
    private val context: Context,
    private val view: UsersMVP.View,
) : UsersMVP.Presenter {
    private val interactor = UserInteractor(this)

    override val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
        Log.d("User", "presenter exception $throwable")
    }

    override val presenterScope =
        CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun getUsers() {
        Log.d("User", "presenter getUsers")
        presenterScope.launch {
            interactor.getUsers(context)
        }
    }

    override fun onGetUsersSuccess(users: List<User>) {
        if (presenterScope.isActive) {
            view.onGetUsersSuccess(users)
        }
    }

    override fun getAlbumsByUserId(userId: Int) {
        Log.d("User", "presenter getAlbumsByUserId")
        presenterScope.coroutineContext.cancelChildren()
        presenterScope.launch {
            interactor.getAlbumsByUserId(userId)
        }
    }

    override fun onGetAlbumsByUserIdSuccess(albums: List<Album>) {
        if (presenterScope.isActive) {
            Log.d("User", "presenter success albums -> isActive")
            view.onGetAlbumsByUserIdSuccess(albums)
        } else Log.d("User", "presenter success albums -> isInactive")
    }

    override fun onError(e: Throwable, retryService: RetryService) {
        if (presenterScope.isActive) {
            Log.d("User", "presenter onError -> isActive")
            view.onError(e, retryService)
        } else Log.d("User", "presenter onError -> isInactive")
    }
}