package com.juarez.mvpcoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.juarez.mvpcoroutines.models.User
import com.juarez.mvpcoroutines.presenter.UserPresenter

class MainActivity : AppCompatActivity(), InterfaceMVP.View {
    private val presenter = UserPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.getUsers()
    }

    override fun onGetUsersSuccess(users: List<User>) {
        users.forEach { user ->
            Log.d("User", user.name)

        }.also {
            presenter.getUserById(users[0].id)
        }
    }

    override fun onGetUserByIdSuccess(user: User) {
        Log.d("User", "getUserById " + user.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onCleared()
    }
}