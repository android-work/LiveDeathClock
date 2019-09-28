package com.android.jay.livedeathclock.utils


import android.content.Context
import com.android.jay.livedeathclock.SP

/**
 * @author Mr.Liu
 * */
object SpUtils {

    fun saveBoolean(context: Context,key: String,value: Boolean){
        val sp = context.getSharedPreferences(SP, 0)
        val edit = sp.edit()
        edit.putBoolean(key,value)
        edit.commit()
    }

    fun getBoolean(context: Context,key: String,defValue: Boolean): Boolean = context.getSharedPreferences(SP, 0).getBoolean(key,defValue)

    fun saveString(context: Context,key: String,value: String?){
        val sp = context.getSharedPreferences(SP, 0)
        val edit = sp.edit()
        edit.putString(key,value)
        edit.commit()
    }

    fun getString(context: Context,key: String,defValue: String): String? = context.getSharedPreferences(SP,0).getString(key,defValue)

    fun saveInt(context: Context,key: String,value: Int){
        val sp = context.getSharedPreferences(SP, 0)
        val edit = sp.edit()
        edit.putInt(key,value)
        edit.commit()
    }

    fun getInt(context: Context,key: String,defValue: Int): Int? = context.getSharedPreferences(SP,0).getInt(key,defValue)
}