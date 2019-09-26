package com.android.jay.livedeathclock.view.viewgroup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.android.jay.livedeathclock.BIR_DATE
import com.android.jay.livedeathclock.LIVE_YEARS
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.interfaces.SplashUpDateUIListener
import com.android.jay.livedeathclock.utils.DateUtil
import com.android.jay.livedeathclock.utils.SpUtils
import com.android.jay.livedeathclock.view.activity.MainActivity
import com.android.jay.livedeathclock.view.dialog.SplashLiveDialog
import com.android.jay.livedeathclock.view.popupwindow.SplashWheelViewPopup
import kotlinx.android.synthetic.main.splash_layout.*
import kotlinx.android.synthetic.main.splash_set_layout.view.*
import java.util.*

/**
 * @author Mr.Liu
 * */
class SplashSetView : FrameLayout {

    var mActivity: Activity? = null
    var mContext: Context? = null

    constructor(context: Context):this(context,null)
    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0)
    constructor(context: Context,attrs: AttributeSet?,defStyleAttr: Int):super(context,attrs,defStyleAttr){

        mContext = context

        LayoutInflater.from(context).inflate(R.layout.splash_set_layout,this,true)

        splash_bir_text.setOnClickListener{ view ->

            //展示出生弹窗
            showWheelView()
        }

        splash_live_text.setOnClickListener { view ->

            //弹出选择生命弹窗
            showLiveYear()
        }

        splash_over.setOnClickListener { view ->
            //完成按钮
            mActivity?.startActivity(Intent(mContext, MainActivity::class.java))

            mActivity?.finish()

            mActivity = null
        }

        initView()
    }

    /**
     * 初始化
     * */
    private fun initView() {

        var date = "${Calendar.getInstance().get(Calendar.YEAR)}-${String.format("%02d",Calendar.getInstance().get(Calendar.MONTH))}-${String.format("%02d",Calendar.getInstance().get(Calendar.DAY_OF_MONTH))}"
        splash_bir_text.text = "${SpUtils.getString(context, BIR_DATE,"${date} 00:00:00")}"

        splash_live_text.text = "${SpUtils.getInt(context, LIVE_YEARS,90)}"

        splash_over.text = mContext?.getString(R.string.splash_popup_over)
    }

    /**
     * 展示生命弹窗
     * */
    private fun showLiveYear() {

        val splashLiveDialog = SplashLiveDialog(context)
        splashLiveDialog.showDialog()

        //更新生命年限ui
        splashLiveDialog.setSplashUpDateUIListener(object: SplashUpDateUIListener {
            override fun splashUpdate(date: String) {
                splash_live_text.text = date

                //保存数据
                SpUtils.saveInt(context, LIVE_YEARS,DateUtil.numStr2Int(date))
            }
        })
    }

    /**
     * 展示出生弹窗
     * */
    private fun showWheelView() {

        val splashWheelViewPopup = SplashWheelViewPopup(context)
        splashWheelViewPopup.show(splash_bir_text)

        //更新选择出生日期ui
        splashWheelViewPopup.setSplashUpDateUIListener(object : SplashUpDateUIListener{
            override fun splashUpdate(date: String) {
                splash_bir_text.text = date

                //保存数据
                SpUtils.saveString(context, BIR_DATE,date)
            }

        })
    }


    /**
     * 获取activity
     * */
    fun setActivity(activity: Activity){
        mActivity = activity
    }


}