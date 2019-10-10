package com.android.jay.livedeathclock.view.fragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.ancroid.work.circleview.DrawGeZiView
import com.ancroid.work.clockview.ClockView
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.imp.DeathPresenter
import com.android.jay.livedeathclock.interfaces.view.IView
import com.android.jay.livedeathclock.utils.logUtil
import com.android.jay.livedeathclock.view.fragment.base.BaseFragment
import com.android.jay.livedeathclock.view.viewgroup.LiveDeathTitleView

class DeathFragment: BaseFragment(),IView.IDeathView {

    var deathView: View? = null
    var deathClock: ClockView? = null
    var deathPresenter: DeathPresenter? = null
    var deathSecond: AppCompatTextView? = null
    var deathDay: AppCompatTextView? = null
    var deathMonth: AppCompatTextView? = null
    var deathYear: AppCompatTextView? = null

    /**
     * 初始化base布局
     * */
    override fun initBaseLayout(view: View) {

        var titleView = view.findViewById<LiveDeathTitleView>(R.id.fragment_base)
        titleView?.setBackgroundColor(resources.getColor(R.color.fragment_death_bg_color))
        titleView?.setMenuRes(R.mipmap.ic_menu_white)
        titleView?.setTitle(R.string.fragment_death_tilte)
        titleView?.setTitleColor(resources.getColor(R.color.fragment_death_title_color))

        deathView = LayoutInflater.from(mContext).inflate(R.layout.fragment_death_layout, null)
        titleView?.addViews(deathView!!)
    }

    /**
     * 初始化数据
     * */
    override fun initData() {

        deathPresenter = DeathPresenter(mContext!!)
        deathPresenter?.bindView(this)

        //初始化时钟
        initClock()
        //初始化底部制定的计划
        initWish()

        //绘制生命格子
        deathPresenter?.calculateDrawGeZi()
        //绘制生命剩余电池
        deathPresenter?.calculateReminderLive()
        //绘制生命秒倒计时
        deathPresenter?.calculateSecondCountdown()
        //绘制生命倒计时
        deathPresenter?.calculateLiveCountdown()

    }

    /**
     * chu初始化死亡页面的愿望单
     * */
    private fun initWish() {

        val makeWishFl = deathView?.findViewById<FrameLayout>(R.id.fragment_death_make_wish_fl)

        //判断加载哪个布局

    }

    /**
     * 绘制格子
     * */
    override fun callBackDrawGeZi(total: Int, passAway: Int) {

        //初始化格子
        initGeZi(total,passAway)

    }

    /**
     * 生命余生电池绘制
     * */
    override fun callBackDrawBattery(batteryId: Int, progress: String) {

        //初始化电池
        initBattery(batteryId,progress)
    }

    /**
     * 生命倒计时回调
     *
     * @param second 秒倒计时
     * */
    override fun callBackSecondCountdown(second: Long) {

        deathSecond?.text = resources.getString(R.string.fragment_death_second,second)

    }

    /**
     * 生命倒计时回调
     *
     * @param day 天倒计时
     *
     * @param month 月倒计时
     *
     * @param year 年倒计时
     * */
    override fun callBackLiveCountdown(day: Long, month: Int, year: Int) {

        logUtil("tag","$day - $month - $year")

        deathDay?.text = resources.getString(R.string.fragment_death_day,day)
        deathMonth?.text = resources.getString(R.string.fragment_death_month,month)
        deathYear?.text = resources.getString(R.string.fragment_death_year,year)
    }


    /**
     * 初始化绘制电池
     *
     * @param batteryId 电池资源
     *
     * @param progress 生命剩余进度
     * */
    private fun initBattery(batteryId: Int, progress: String) {

        val deathLiveIc = deathView?.findViewById<AppCompatImageView>(R.id.fragment_death_remind_live_ic)
        val deathLiveProgress = deathView?.findViewById<AppCompatTextView>(R.id.fragment_death_remind_live_tv)
        deathLiveIc?.setImageResource(batteryId)
        deathLiveProgress?.text = progress

        deathSecond = deathView?.findViewById(R.id.fragment_death_second)
        deathDay = deathView?.findViewById(R.id.fragment_death_day)
        deathMonth = deathView?.findViewById(R.id.fragment_death_month)
        deathYear = deathView?.findViewById(R.id.fragment_death_year)

    }

    /**
     * 初始化格子
     * */
    private fun initGeZi(total: Int, passAway: Int) {

        logUtil("tag","total$total:passAway:$passAway")

        val deathGeZiView = deathView?.findViewById<DrawGeZiView>(R.id.fragment_death_gezi_view)
        logUtil("tag","deathGeZiView:$deathGeZiView")
        deathGeZiView?.setGeZiCount(total)
        deathGeZiView?.setGeZiBg(Color.WHITE)
        deathGeZiView?.setFillColor(resources.getColor(R.color.fragment_death_bg_color))
        deathGeZiView?.setGeZiLinecolor(resources.getColor(R.color.fragment_death_gezi_line_color))
        deathGeZiView?.setFillCount(passAway)
    }

    /**
     * 初始化时钟
     * */
    private fun initClock() {

        deathClock = deathView?.findViewById<ClockView>(R.id.fragment_death_clock)
        deathClock?.setClockColor(resources.getColor(R.color.fragment_death_title_color))
        deathClock?.setMinuteColor(resources.getColor(R.color.fragment_death_title_color))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        deathClock?.stop()
        deathClock = null

        deathPresenter?.unBindView()
        deathPresenter = null

        deathSecond = null
        deathDay = null
        deathMonth = null
        deathYear = null
    }

}