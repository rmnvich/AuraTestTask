package com.aura.test.vromanovich.data.storage

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : SharedPreferencesStorage {

    override fun get(name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}