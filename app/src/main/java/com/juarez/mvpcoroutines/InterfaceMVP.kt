package com.juarez.mvpcoroutines

import com.juarez.mvpcoroutines.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

interface InterfaceMVP {

    interface View {
        fun onGetUsersSuccess(users: List<User>)
        fun onGetUserByIdSuccess(user: User)
    }

    interface Presenter {
        val presenterScope: CoroutineScope
        fun getUsers()
        fun onGetUsersSuccess(users: List<User>)
        fun getUserById(userId: Int)
        fun onGetUserByIdSuccess(user: User)
        fun onCleared() = presenterScope.cancel()
    }

    interface Interactor {
        suspend fun getUsers()
        suspend fun getUserById(userId: Int)
    }
}