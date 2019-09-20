package com.android.jay.livedeathclock.view.popupwindow

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.interfaces.SplashUpDateUIListener
import com.android.jay.livedeathclock.utils.DateUtil
import com.android.jay.livedeathclock.utils.logUtil
import com.wx.wheelview.adapter.ArrayWheelAdapter
import com.wx.wheelview.widget.WheelView
import java.util.*

/**
 * @author Mr.Liu
 * */
class SplashWheelViewPopup constructor( var context: Context): PopupWindow(), View.OnClickListener {

    var view: View = LayoutInflater.from(context).inflate(R.layout.splash_popup_view, null)
    var mSplashDownIc: AppCompatImageView? = null
    var mSplashOver: AppCompatTextView? = null
    var mSplashWheelYear: WheelView<String>? = null
    var mSplashWheelMonth: WheelView<String>? = null
    var mSplashWheelDate: WheelView<String>? = null
    var mSplashWheelHour: WheelView<String>? = null
    var mSplashWheelMinute: WheelView<String>? = null
    var mSplashWheelSecond: WheelView<String>? = null
    var mSplashUpDateUIListener: SplashUpDateUIListener? = null
    var year: Int = Calendar.getInstance().get(Calendar.YEAR)
    var month = Calendar.getInstance().get(Calendar.MONTH) + 1
    var date = Calendar.getInstance().get(Calendar.DATE)
    var hour = 0
    var minute = 0
    var second = 0


    /**
     * 初始化代码块
     * */
    init {
        initView()

        this.contentView = view
        this.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.isTouchable = true
        this.isFocusable = true
        this.isOutsideTouchable = true
        this.width = LinearLayout.LayoutParams.MATCH_PARENT
        this.height = LinearLayout.LayoutParams.WRAP_CONTENT
        this.animationStyle = R.style.PopupWindowAnimator

        initDate()

        setListener()
    }

    /**
     * 设置wheelview的数据
     * */
    private fun initDate() {
        mSplashWheelYear?.setWheelAdapter(ArrayWheelAdapter(context))// 文本数据源
        mSplashWheelYear?.setWheelData(DateUtil.getBirYear())

        mSplashWheelMonth?.setWheelAdapter(ArrayWheelAdapter(context))// 文本数据源
        mSplashWheelMonth?.setWheelData(DateUtil.getBirMonth(year))// 文本数据源

        mSplashWheelDate?.setWheelAdapter(ArrayWheelAdapter(context))// 文本数据源
        mSplashWheelDate?.setWheelData(DateUtil.getBirDate(year,month))

        mSplashWheelMinute?.setWheelAdapter(ArrayWheelAdapter(context))// 文本数据源
        mSplashWheelMinute?.setWheelData(DateUtil.getBirMinOrSec())

        mSplashWheelHour?.setWheelAdapter(ArrayWheelAdapter(context))// 文本数据源
        mSplashWheelHour?.setWheelData(DateUtil.getBirHour())

        mSplashWheelSecond?.setWheelAdapter(ArrayWheelAdapter(context))// 文本数据源
        mSplashWheelSecond?.setWheelData(DateUtil.getBirMinOrSec())

    }

    private fun setListener() {

        mSplashDownIc?.setOnClickListener(this)
        mSplashOver?.setOnClickListener(this)


        /**wheelview控件的选择监听*/

        mSplashWheelYear?.setOnWheelItemSelectedListener { position, t ->
            year = DateUtil.numStr2Int(t)
            //选择年份，同时刷新月份数据
            mSplashWheelMonth?.setWheelData(DateUtil.getBirMonth(year))
            //刷新日期
            mSplashWheelDate?.setWheelData(DateUtil.getBirDate(year,DateUtil.numStr2Int(mSplashWheelMonth?.selectionItem?: "${Calendar.MONTH}")))
        }

        mSplashWheelMonth?.setOnWheelItemSelectedListener { position, t ->
            month = DateUtil.numStr2Int(t)
            //刷新日期
            mSplashWheelDate?.setWheelData(DateUtil.getBirDate(year,month))
        }

        mSplashWheelDate?.setOnWheelItemSelectedListener { position, t ->
            date = DateUtil.numStr2Int(t)
        }

    }

    /**
     * 控件初始化
     * */
    private fun initView() {

        mSplashDownIc = view.findViewById<AppCompatImageView>(R.id.splash_down_ic)
        mSplashOver = view.findViewById<AppCompatTextView>(R.id.splash_over)
        mSplashWheelYear = view.findViewById<WheelView<String>>(R.id.splash_wheel_year)
        mSplashWheelMonth = view.findViewById<WheelView<String>>(R.id.splash_wheel_month)
        mSplashWheelDate = view.findViewById<WheelView<String>>(R.id.splash_wheel_date)
        mSplashWheelHour = view.findViewById<WheelView<String>>(R.id.splash_wheel_hour)
        mSplashWheelMinute = view.findViewById<WheelView<String>>(R.id.splash_wheel_minute)
        mSplashWheelSecond = view.findViewById<WheelView<String>>(R.id.splash_wheel_second)
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.splash_down_ic -> this.dismiss()
            R.id.splash_over -> {
                val selectYear = mSplashWheelYear?.selectionItem
                val selectMonth = String.format("%02d",DateUtil.numStr2Int(mSplashWheelMonth?.selectionItem ?: "$month"))
                val selectDate = String.format("%02d",DateUtil.numStr2Int(mSplashWheelDate?.selectionItem ?: "$date"))
                var selectHour = String.format("%02d",DateUtil.numStr2Int(mSplashWheelHour?.selectionItem ?: "$hour"))
                var selectMinute = String.format("%02d",DateUtil.numStr2Int(mSplashWheelMinute?.selectionItem ?: "$minute"))
                var selectSecond = String.format("%02d",DateUtil.numStr2Int(mSplashWheelSecond?.selectionItem ?: "$second"))

                logUtil(javaClass.name,"$selectYear-$selectMonth-$selectDate $selectHour:$selectMinute:$selectSecond")

                mSplashUpDateUIListener?.splashUpdate("$selectYear-$selectMonth-$selectDate $selectHour:$selectMinute:$selectSecond")

                this.dismiss()
            }
        }
    }

    /**
     * 显示弹窗
     * */
    fun show(view: View){
        this.showAtLocation(view,Gravity.BOTTOM,0,0)
    }

    /**
     * 选择日期后更新ui回调
     * */
    fun setSplashUpDateUIListener(splashUpDateUIListener: SplashUpDateUIListener){
        mSplashUpDateUIListener = splashUpDateUIListener
    }
}