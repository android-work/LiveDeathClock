package com.android.jay.livedeathclock.view.fragment

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.interfaces.view.IView
import com.android.jay.livedeathclock.utils.logUtil
import com.android.jay.livedeathclock.view.fragment.base.BaseFragment
import com.android.jay.livedeathclock.view.viewgroup.LiveDeathTitleView
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
        initChildView(view)
    }

    /**
     * 加载子布局并添加
     * */
    private fun initChildView(view: View) {
        liveView = LayoutInflater.from(mContext).inflate(R.layout.fragment_live_layout, null)

        //初始化布局容器，添加子布局
        val baseContent = view.findViewById<LiveDeathTitleView>(R.id.fragment_base)
        baseContent.addView(liveView)
    }


    /**
     * 加载布局
     * */
    override fun initView(): Int {
        return R.layout.fragment_live_layout
    }

}