package com.android.jay.livedeathclock.imp

import android.content.Context
import com.android.jay.livedeathclock.IS_SHOW_SPLASH
import com.android.jay.livedeathclock.interfaces.presenter.IPresenter
import com.android.jay.livedeathclock.interfaces.view.IView
import com.android.jay.livedeathclock.utils.SpUtils

/**
 * @author Mr.Liu
 * */
class SplashPresenter constructor(var context: Context): IPresenter.ISplashPresenter {

    var mSplashView: IView.ISplashView? = null

    /**
     * 解绑view
     * */
    override fun unBindView() {
        mSplashView = null
    }

    /**
     * 绑定view
     * @param 'view对象'
     * */
    override fun bindView(splashView: IView.ISplashView) {
        mSplashView = splashView
    }

    /**
     * 判断是否展示引导页
     * */
    override fun isShowSplash() {
        val isShow = SpUtils.getBoolean(context, IS_SHOW_SPLASH, true)
        mSplashView?.callBackIsShowSplash(isShow)
    }
}