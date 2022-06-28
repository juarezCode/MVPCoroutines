package com.juarez.mvpcoroutines.interactor

import android.content.Context
import android.util.Log
import com.juarez.mvpcoroutines.common.RetryService
import com.juarez.mvpcoroutines.common.UsersMVP
import com.juarez.mvpcoroutines.data.UserRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserInteractor(private val presenter: UsersMVP.Presenter) : UsersMVP.Interactor {
    private val repository = UserRepository()

    override suspend fun getUsers(context: Context) {
        // use context in service, prefs, etc
        try {
            val users = withContext(Dispatchers.IO) {
                repository.getUsers()
            }
            presenter.onGetUsersSuccess(users)
        } catch (e: CancellationException) {
            Log.d("User", "interactor getUsers CancellationException $e")
        } catch (e: Throwable) {
            Log.d("User", "interactor getUsers $e")
            presenter.onError(e, RetryService.GET_USERS)
        }

    }

    override suspend fun getAlbumsByUserId(userId: Int) {
        try {
            val albums = withContext(Dispatchers.IO) {
                repository.getAlbumsByUserId(userId)
            }
            presenter.onGetAlbumsByUserIdSuccess(albums)
        } catch (e: Throwable) {
            Log.d("User", "interactor getAlbumsByUserId $e")
            presenter.onError(e, RetryService.GET_USER_BY_ID)
        }
    }
}