package com.juarez.mvpcoroutines.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.juarez.mvpcoroutines.R
import com.juarez.mvpcoroutines.common.RetryService
import com.juarez.mvpcoroutines.common.UsersMVP
import com.juarez.mvpcoroutines.models.Album
import com.juarez.mvpcoroutines.models.User
import com.juarez.mvpcoroutines.presenter.UserPresenter
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.json.JSONException
import java.io.IOException

class MainActivity : AppCompatActivity(), UsersMVP.View {
    private val presenter = UserPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("User", "MainActivity -> getting users..")
        presenter.getUsers()
        runBlocking {
            delay(2000)
            presenter.getAlbumsByUserId(1)
        }
    }

    override fun onGetUsersSuccess(users: List<User>) {
        Log.d("User", "MainActivity -> onGetUsersSuccess $users")
//        startActivity(Intent(this, SecondActivity::class.java))
    }

    override fun onGetAlbumsByUserIdSuccess(albums: List<Album>) {
        Log.d("User", "MainActivity -> onGetAlbumsByUserId $albums")
    }

    override fun onError(e: Throwable, retryService: RetryService) {
        Log.d("User", "MainActivity -> onError")

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
        Log.d("User", "MainActivity -> onDestroy")
        presenter.onDestroy()
    }
}