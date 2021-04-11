package com.example.factsapp.shared_preference

import android.content.Context
import android.content.SharedPreferences
import com.example.factsapp.Constants


class SharedPreferenceRepository(private val context: Context) {
    private var editor: SharedPreferences.Editor
    private var sharedPreferenceHelper: SharedPreferences

    init {
        sharedPreferenceHelper = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferenceHelper.edit()
    }


    fun saveString(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(key: String): String? {
        return sharedPreferenceHelper.getString(key, null)
    }
}