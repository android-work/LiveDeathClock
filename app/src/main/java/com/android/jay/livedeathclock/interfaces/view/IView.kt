package com.android.jay.livedeathclock.interfaces.view

interface IView {

    /**
     * SplashActivity的View接口
     * */
    interface ISplashView{
        //是否是展示欢迎页的ui处理回调
        fun callBackIsShowSplash(isShow: Boolean)
    }

    /**
     * LiveFragment的View接口
     * */
    interface ILiveView{

        //计算年龄接口回调Ui更新
        fun callBackCalculateAge(age: Int)
    }
}