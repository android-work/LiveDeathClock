package com.android.jay.livedeathclock.imp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.android.jay.livedeathclock.interfaces.presenter.IPresenter
import com.android.jay.livedeathclock.interfaces.view.IView
import com.android.jay.livedeathclock.receiver.SingleReceiver
import com.android.jay.livedeathclock.utils.DateUtil
import com.android.jay.livedeathclock.utils.logUtil

/**
 *@author Mr.Liu
 * */
class LivePresenter(var context: Context) : IPresenter.ILivePresenter {

    var mLiveView:IView.ILiveView? = null

    /**
     * 计算年龄
     * */
    override fun calculateAge() {


    }

    /**
     * 绑定fragment
     * */
    override fun bindView(liveView: IView.ILiveView) {

        mLiveView = liveView

        logUtil("tag","绑定view")

        SingleReceiver.getInstance().regist(context)
        SingleReceiver.getInstance().setBroadcastReceiverListener { ->
            logUtil("tag","分钟广播回调")
            mLiveView?.callBackCalculateMinute()
        }
    }

    /**
     * 解绑
     * */
    override fun unBindView() {

        mLiveView = null
        SingleReceiver.getInstance().unRegist(context)
    }
}