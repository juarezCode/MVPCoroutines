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

class SecondActivity : AppCompatActivity(), UsersMVP.View {
    private val presenter = UserPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d("User", "SecondActivity -> getting albums..")
        presenter.getAlbumsByUserId(1)
    }

    override fun onGetUsersSuccess(users: List<User>) {
        Log.d("User", "SecondActivity -> onGetUsersSuccess")
    }

    override fun onGetAlbumsByUserIdSuccess(albums: List<Album>) {
        Log.d("User", "SecondActivity -> onGetAlbumsByUserIdSuccess $albums")

    }

    override fun onError(e: Throwable, retryService: RetryService) {
        Log.d("User", "SecondActivity -> exception ${e.localizedMessage} ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("User", "SecondActivity -> onDestroy")
        presenter.onDestroy()
    }
}