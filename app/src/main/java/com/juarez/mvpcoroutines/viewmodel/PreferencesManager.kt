package com.juarez.mvpcoroutines.viewmodel

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private var preferences: SharedPreferences = context.getSharedPreferences(
        FILE_PREFERENCES,
        Context.MODE_PRIVATE
    )

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    companion object {
        private const val DEFAULT_INT = -1
        private const val DEFAULT_BOOLEAN = false
        private const val FILE_PREFERENCES = "app_preferences"
        private var INSTANCE: PreferencesManager? = null

        @JvmStatic
        fun getInstance(context: Context): PreferencesManager {
            if (INSTANCE == null) INSTANCE = PreferencesManager(context)
            return INSTANCE!!
        }
    }

    fun getInt(key: String): Int? = preferences.getInt(key, DEFAULT_INT).let { value ->
        if (value == DEFAULT_INT) return null
        return value
    }

    fun getString(key: String): String? = preferences.getString(key, null)

    fun getBoolean(key: String): Boolean = preferences.getBoolean(key, DEFAULT_BOOLEAN)

    fun put(key: String, value: String?) = preferences.edit { it.putString(key, value) }

    fun put(key: String, value: Int) = preferences.edit { it.putInt(key, value) }

    fun put(key: String, value: Boolean) = preferences.edit { it.putBoolean(key, value) }

    fun getAge() = getInt("age")
    fun putAge(age: Int) = put("age", age)
}