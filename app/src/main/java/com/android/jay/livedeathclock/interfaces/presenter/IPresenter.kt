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
        fun bindView(splashView: IView.ISplashView)

        //解绑view
        fun unBindView()
    }
}