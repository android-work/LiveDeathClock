package com.android.jay.livedeathclock.utils

import android.util.Log
import com.android.jay.livedeathclock.LOG

fun logUtil(tag: String,log: String){
    if (LOG){
        Log.e(tag,log)
    }
}