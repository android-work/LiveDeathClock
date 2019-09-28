package com.android.jay.livedeathclock.view.fragment

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.android.jay.livedeathclock.BIR_DATE
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.interfaces.view.IView
import com.android.jay.livedeathclock.utils.DateUtil
import com.android.jay.livedeathclock.utils.SpUtils
import com.android.jay.livedeathclock.utils.logUtil
import com.android.jay.livedeathclock.view.fragment.base.BaseFragment
import com.android.jay.livedeathclock.view.viewgroup.LiveBottomViewGroup
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

        val btnTopView = liveView?.findViewById<LiveBottomViewGroup>(R.id.fragment_btn_top_view)
        val btnBottomView = liveView?.findViewById<LiveBottomViewGroup>(R.id.fragment_btn_bottom_view)

        initBottomView(btnTopView!!,btnBottomView!!)

    }

    /**
     * 初始化live底部时间布局
     *
     * @param btnTopView 第一个布局
     *
     * @param btnBottomView 第二个布局
     * */
    private fun initBottomView(btnTopView: LiveBottomViewGroup, btnBottomView: LiveBottomViewGroup) {

        val birMillis = DateUtil.dateStr2Millis(SpUtils.getString(mContext!!, BIR_DATE, "")!!, "yyyy-MM-DD hh:mm:ss")
        logUtil("tag","birMillis:$birMillis")

        btnTopView.setTopOneText(mContext?.resources!!.getString(R.string.year))
        btnTopView.setTopTwoText(mContext?.resources!!.getString(R.string.month))
        btnTopView.setTopThreeText(mContext?.resources!!.getString(R.string.week))

        btnTopView.setBottomOneText("${DateUtil.calculationAge(birMillis)}")
        btnTopView.setBottomTwoText("${DateUtil.calculationMonths(birMillis)}")
        btnTopView.setBottomThreeText("${DateUtil.calculationWeeks(birMillis)}")

        btnBottomView.setBottomOneText(mContext?.resources!!.getString(R.string.day))
        btnBottomView.setBottomTwoText(mContext?.resources!!.getString(R.string.hour))
        btnBottomView.setBottomThreeText(mContext?.resources!!.getString(R.string.minute))

        btnBottomView.setBottomOneText("${DateUtil.calculationDays(birMillis)}")
        btnBottomView.setBottomTwoText("${DateUtil.calculationHours(birMillis)}")
        btnBottomView.setBottomThreeText("${DateUtil.calculationMinutes(birMillis)}")

    }


    /**
     * 加载布局
     * */
    override fun initView(): Int {
        return R.layout.fragment_live_layout
    }

}