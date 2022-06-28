package com.juarez.mvpcoroutines.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class PresenterViewModel(context: Application) : AndroidViewModel(context) {
    private val prefs = PreferencesManager(context)

    fun save() {
        Log.d("MainViewModel", "save")
        prefs.putAge(26)
    }

    fun getAge() {
        Log.d("MainViewModel", "getting...")
        prefs.getAge()?.let { age ->
            Log.d("MainViewModel", "age $age")
        }
    }
}