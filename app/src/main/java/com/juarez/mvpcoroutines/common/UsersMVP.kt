package com.juarez.mvpcoroutines.common

import android.content.Context
import com.juarez.mvpcoroutines.models.Album
import com.juarez.mvpcoroutines.models.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

interface UsersMVP {

    interface View {
        fun onGetUsersSuccess(users: List<User>)
        fun onGetAlbumsByUserIdSuccess(albums: List<Album>)
        fun onError(e: Throwable, retryService: RetryService)
    }

    interface Presenter {
        val exceptionHandler: CoroutineExceptionHandler
        val presenterScope: CoroutineScope
        fun getUsers()
        fun onGetUsersSuccess(users: List<User>)
        fun getAlbumsByUserId(userId: Int)
        fun onGetAlbumsByUserIdSuccess(albums: List<Album>)
        fun onDestroy() = presenterScope.cancel()
        fun onError(e: Throwable, retryService: RetryService)
    }

    interface Interactor {
        suspend fun getUsers(context: Context)
        suspend fun getAlbumsByUserId(userId: Int)
    }
}