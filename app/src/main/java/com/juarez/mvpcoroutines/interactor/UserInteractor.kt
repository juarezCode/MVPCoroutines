package com.juarez.mvpcoroutines.interactor

import android.content.Context
import com.juarez.mvpcoroutines.common.RetryService
import com.juarez.mvpcoroutines.common.UsersMVP
import com.juarez.mvpcoroutines.data.UserRepository
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
        } catch (e: Throwable) {
            presenter.onError(e, RetryService.GET_USERS)
        }

    }

    override suspend fun getUserById(userId: Int) {
        try {
            val user = withContext(Dispatchers.IO) {
                repository.getUserByIdService(userId)
            }
            presenter.onGetUserByIdSuccess(user)
        } catch (e: Throwable) {
            presenter.onError(e, RetryService.GET_USER_BY_ID)
        }
    }
}