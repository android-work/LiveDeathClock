package com.android.jay.livedeathclock.utils

import android.content.Context

/**
 * @author Mr.Liu
 * */
object Utlis{

    fun dp2Px(context: Context,dp: Float): Float{

        val density = context.resources.displayMetrics.density
        return (dp * density + 0.5f)
    }
}