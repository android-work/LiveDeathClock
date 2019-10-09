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

        //计算分钟回调
        fun callBackCalculateMinute()
    }

    /**
     * DeathFragment的view接口
     * */
    interface IDeathView{

        //绘制、填充格子回调
        fun callBackDrawGeZi(total: Int,passAway: Int)

        //绘制余生生命电池
        fun callBackDrawBattery(batteryId: Int,progress: String)

        //生命倒计时秒更新
        fun callBackSecondCountdown(second: Long)

        //生命倒计时秒更新
        fun callBackLiveCountdown( day: Long, month: Int, year: Int)
    }
}