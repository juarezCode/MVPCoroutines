package com.juarez.mvpcoroutines.interactor

import com.juarez.mvpcoroutines.InterfaceMVP
import com.juarez.mvpcoroutines.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserInteractor(private val presenter: InterfaceMVP.Presenter) : InterfaceMVP.Interactor {
    private val repository = UserRepository()

    override suspend fun getUsers() {
        val users = withContext(Dispatchers.IO) {
            repository.getUsers()
        }
        presenter.onGetUsersSuccess(users)
    }

    override suspend fun getUserById(userId: Int) {
        val user = withContext(Dispatchers.IO) {
            repository.getUserByIdService(userId)
        }
        presenter.onGetUserByIdSuccess(user)
    }
}