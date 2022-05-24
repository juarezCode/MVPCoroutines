package com.juarez.mvpcoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.juarez.mvpcoroutines.common.RetryService
import com.juarez.mvpcoroutines.common.UsersMVP
import com.juarez.mvpcoroutines.models.User
import com.juarez.mvpcoroutines.presenter.UserPresenter
import org.json.JSONException
import java.io.IOException

class MainActivity : AppCompatActivity(), UsersMVP.View {
    private val presenter = UserPresenter(this, this)

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

    override fun onError(e: Throwable, retryService: RetryService) {

        when (e) {
            is IOException -> {
                Log.d("User", "No internet connection ")
            }
            is JSONException -> {
                Log.d("User", "Error parsing JSON Object ")
            }
            else -> {
                Log.d("User", "exception ${e.localizedMessage} ")
                when (retryService) {
                    RetryService.GET_USERS -> {
                        Log.d("User", "Retry in GET USERS ERROR ")
                    }
                    RetryService.GET_USER_BY_ID -> {
                        Log.d("User", "Retry in GET USER BY ID ERROR ")
                    }
                    else -> {
                        Log.d("User", "Retry in DEFAULT ERROR ")

                    }
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onCleared()
    }
}