package com.example.composetask.core.presentation.common

import android.content.Context
import android.content.SharedPreferences
import com.example.composetask.authentication.presentation.login.validate.LoginRequest
import com.google.gson.Gson

@Suppress("UNCHECKED_CAST")
class SharedPrefs(context: Context) {
    companion object {
        private const val PREF = "PrefHoorBookCompose"
        private const val PREF_TOKEN = "user_token"
        private const val SAVE_USER = "SAVE_USER"
    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun saveUser(user: LoginRequest?) {
        put(SAVE_USER, Gson().toJson(user))
    }

    fun getUser(): LoginRequest? {
        val data = get(SAVE_USER, String::class.java)
        if (data.isEmpty()) {
            return null
        }
        return Gson().fromJson(data, LoginRequest::class.java)
    }

    fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear() {
        sharedPref.edit().run {
            remove(PREF_TOKEN)
        }.apply()
    }

    fun getPreferredLocaleCompose(): String {
        return sharedPref.getString("preferred_locale_compose", "en")!!
    }

    fun setPreferredLocaleCompose(localeCode: String) {
        sharedPref.edit().putString("preferred_locale_compose", localeCode).apply()
    }

}