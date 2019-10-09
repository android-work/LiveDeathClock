package com.android.jay.livedeathclock.view.fragment

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.ancroid.work.clockview.ClockView
import com.android.jay.livedeathclock.BIR_DATE
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.imp.LivePresenter
import com.android.jay.livedeathclock.interfaces.presenter.IPresenter
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
    var liveClock: ClockView? = null
    var btnTopView: LiveBottomViewGroup? = null
    var btnBottomView: LiveBottomViewGroup? = null
    var livePresenter: IPresenter.ILivePresenter? = null

    /**
     * 年龄计算完成，更新ui
     * */
    override fun callBackCalculateAge(age: Int) {


    }

    /**
     * 活得分钟计算
     * */
    override fun callBackCalculateMinute() {

        logUtil("tag","广播更新")
        initBottomView(btnTopView,btnBottomView)
    }

    /**
     * 初始化数据
     * */
    override fun initData() {

        //加载子布局，初始化
        initChildView()

        //绑定presenter
        livePresenter = LivePresenter(context!!)
        livePresenter?.bindView(this)
    }

    /**
     * 加载子布局并添加
     * */
    private fun initChildView() {

        liveClock = liveView?.findViewById(R.id.fragment_live_clock)
        btnTopView = liveView?.findViewById(R.id.fragment_btn_top_view)
        btnBottomView = liveView?.findViewById(R.id.fragment_btn_bottom_view)
        val liveAge = liveView?.findViewById<AppCompatTextView>(R.id.fragment_live_age)

        liveAge!!.text = resources.getString(R.string.age,DateUtil.calculationAge(DateUtil.dateStr2Millis(SpUtils.getString(mContext!!, BIR_DATE, "")!!, "yyyy-MM-dd HH:mm:ss")))

        initBottomView(btnTopView!!,btnBottomView!!)

    }

    /**
     * 初始化live底部时间布局
     *
     * @param btnTopView 第一个布局
     *
     * @param btnBottomView 第二个布局
     * */
    private fun initBottomView(btnTopView: LiveBottomViewGroup?, btnBottomView: LiveBottomViewGroup?) {

        val birMillis = DateUtil.dateStr2Millis(SpUtils.getString(mContext!!, BIR_DATE, "")!!, "yyyy-MM-dd HH:mm:ss")
        logUtil("tag","millis:${DateUtil.calculationMinutes(birMillis)}")

        btnTopView?.setTopOneText("${DateUtil.calculationAge(birMillis)}")
        btnTopView?.setTopTwoText("${DateUtil.calculationMonths(birMillis)}")
        btnTopView?.setTopThreeText("${DateUtil.calculationWeeks(birMillis)}")

        btnTopView?.setBottomOneText(mContext?.resources!!.getString(R.string.year))
        btnTopView?.setBottomTwoText(mContext?.resources!!.getString(R.string.month))
        btnTopView?.setBottomThreeText(mContext?.resources!!.getString(R.string.week))

        btnBottomView?.setBottomOneText(mContext?.resources!!.getString(R.string.day))
        btnBottomView?.setBottomTwoText(mContext?.resources!!.getString(R.string.hour))
        btnBottomView?.setBottomThreeText(mContext?.resources!!.getString(R.string.minute))

        btnBottomView?.setTopOneText("${DateUtil.calculationDays(birMillis)}")
        btnBottomView?.setTopTwoText("${DateUtil.calculationHours(birMillis)}")
        btnBottomView?.setTopThreeText("${DateUtil.calculationMinutes(birMillis)}")

    }


    /**
     * 初始化base布局
     * */
    override fun initBaseLayout(view: View) {

        liveView = LayoutInflater.from(mContext).inflate(R.layout.fragment_live_layout, null)
        //初始化布局容器，添加子布局
        val baseContent = view.findViewById<LiveDeathTitleView>(R.id.fragment_base)
        baseContent.addViews(liveView!!)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        liveClock?.stop()
        livePresenter?.unBindView()
        livePresenter = null
        liveView = null
        btnTopView = null
        btnBottomView = null
        liveClock = null
    }

}