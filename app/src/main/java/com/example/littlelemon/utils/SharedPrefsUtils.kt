package com.example.littlelemon.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object SharedPreferencesUtils {

    private const val PREFS_NAME = "MyPrefs"
    private const val KEY_FIRST_NAME = "first_name"
    private const val KEY_LAST_NAME = "last_name"
    private const val KEY_EMAIL = "email"

    fun doesSharedPreferencesExist(context: Context): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).contains(
            KEY_FIRST_NAME
        )
    }

    fun saveDataToSharedPreferences(context: Context, firstName: String, lastName: String, email: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        sharedPreferences.edit {
            putString(KEY_FIRST_NAME, firstName)
            putString(KEY_LAST_NAME, lastName)
            putString(KEY_EMAIL, email)
        }
    }

    // You can also create functions to retrieve data from SharedPreferences if needed
    fun getFirstNameFromSharedPreferences(context: Context): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        return sharedPreferences.getString(KEY_FIRST_NAME, "") ?: ""
    }

    fun getLastNameFromSharedPreferences(context: Context): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        return sharedPreferences.getString(KEY_LAST_NAME, "") ?: ""
    }

    fun getEmailFromSharedPreferences(context: Context): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        return sharedPreferences.getString(KEY_EMAIL, "") ?: ""
    }

    fun clearSharedPreferences(context: Context) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        sharedPreferences.edit().clear().apply()
    }
}
