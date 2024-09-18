package com.aura.test.vromanovich.data.storage

import android.content.SharedPreferences

interface SharedPreferencesStorage {

    fun get(name: String): SharedPreferences
}