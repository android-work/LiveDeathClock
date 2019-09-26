package com.android.jay.livedeathclock.interfaces

import android.app.Activity

/**
 * @author Mr.Liu
 *
 * 更新SplashActivity日期回调
 * */
interface SplashUpDateUIListener {

    /**
     * @param date 选择日期更新ui
     * */
    fun splashUpdate(date: String)


}