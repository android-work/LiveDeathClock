package com.android.jay.livedeathclock.interfaces.presenter

import com.android.jay.livedeathclock.interfaces.view.IView

interface IPresenter {

    /**
     * splashActivity的presenter接口
     * */
    interface ISplashPresenter{
        //判断是否展示引导页
        fun isShowSplash()

        //绑定view
        fun bindView(splashView: IView.ISplashView)

        //解绑view
        fun unBindView()
    }

    /**
     * LiveFragment的presenter接口
     * */
    interface ILivePresenter{

        //计算年龄
        fun calculateAge()

        //绑定view
        fun bindView(liveView: IView.ILiveView)

        //解绑view
        fun unBindView()
    }

    /**
     * DeathFragment的presenter接口
     * */
    interface IDeathPresenter{
        
        //获取存放在数据库中的事件
        fun getSqlEvents()

        //计算活了多久，总生命
        fun calculateDrawGeZi()

        //计算余生
        fun calculateReminderLive()

        //计算生命秒、天、月、年倒计时
        fun calculateSecondCountdown()

        //计算生命 天、月、年倒计时
        fun calculateLiveCountdown()

        //绑定view
        fun bindView(deathView: IView.IDeathView)

        //解绑view
        fun unBindView()
    }
}