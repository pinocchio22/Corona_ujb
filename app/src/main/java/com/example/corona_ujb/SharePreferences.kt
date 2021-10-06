package com.example.corona_ujb

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

class SharePreferences : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getAlert(key: String, defValue: Int): Int {
        return prefs.getInt(key, defValue).toInt()
    }
    fun setAlert(key: String, str: Int) {
        prefs.edit().putInt(key, str).apply()
    }
}
