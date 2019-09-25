package com.android.jay.livedeathclock.view.fragment

import android.view.LayoutInflater
import android.view.View
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.interfaces.view.IView
import com.android.jay.livedeathclock.utils.logUtil
import com.android.jay.livedeathclock.view.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_live_layout.*

class LiveFragment: BaseFragment(),IView.ILiveView{


    var liveView: View? = null

    /**
     * 年龄计算完成，更新ui
     * */
    override fun callBackCalculateAge(age: Int) {


    }


    /**
     * 初始化数据
     * */
    override fun initData(view: View) {

        //加载子布局，初始化
        initChildView()
    }

    /**
     * 加载子布局并添加
     * */
    private fun initChildView() {
        liveView = LayoutInflater.from(mContext).inflate(R.layout.fragment_live_layout, null)

        logUtil("tag","------${fragment_live_clock}")
    }


    /**
     * 加载布局
     * */
    override fun initView(): Int {
        return R.layout.fragment_live_layout
    }

}