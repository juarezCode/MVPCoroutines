package com.juarez.mvpcoroutines.presenter

import com.juarez.mvpcoroutines.InterfaceMVP
import com.juarez.mvpcoroutines.interactor.UserInteractor
import com.juarez.mvpcoroutines.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserPresenter(private val view: InterfaceMVP.View) : InterfaceMVP.Presenter {
    private val interactor = UserInteractor(this)

    override val presenterScope = CoroutineScope(Dispatchers.Main + Job())

    override fun getUsers() {
        presenterScope.launch {
            interactor.getUsers()
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
}