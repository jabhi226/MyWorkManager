package com.example.myworkmanagerapp

import android.app.Activity
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {



    fun getTimeInString(): String {
        val simpleDate: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        return simpleDate.format(Date())
    }

    fun getString(context: Context?, key: String?): String {
        var returnString = ""
        if (context != null) {
            val sp = context.getSharedPreferences("WORK_MANAGER_LOGS", Activity.MODE_PRIVATE)
            returnString = sp.getString(key, "").toString()
        }
        return returnString
    }

    fun setString(context: Context?, key: String?, value: String?) {
        if (context != null) {
            val sharedPreferences =
                context.getSharedPreferences("WORK_MANAGER_LOGS", Activity.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
        }
    }
}