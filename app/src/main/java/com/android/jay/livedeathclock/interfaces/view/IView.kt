package com.android.jay.livedeathclock.interfaces.view

interface IView {

    /**
     * SplashActivity的View接口
     * */
    interface ISplashView{
        //是否是展示欢迎页的ui处理回调
        fun callBackIsShowSplash(isShow: Boolean)
    }
}