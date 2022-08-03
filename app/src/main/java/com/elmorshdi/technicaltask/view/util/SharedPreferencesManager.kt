package com.elmorshdi.technicaltask.view.util

import android.content.SharedPreferences


object SharedPreferencesManager {


    fun getToken(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString("TOKEN", "")?:""
    }
    fun getLoginValue(sharedPreferences: SharedPreferences): Boolean {
        return sharedPreferences.getBoolean("LOGGED_IN", false)
    }

    fun signOutShared(editor: SharedPreferences.Editor)  {
        editor.remove("TOKEN")
        editor.putBoolean("LOGGED_IN", false)
         editor.apply()
    }

    fun signInShared(editor: SharedPreferences.Editor, token: String?) {
        editor.putString("TOKEN", token)
        editor.putBoolean("LOGGED_IN", true)
         editor.apply()

    }
}