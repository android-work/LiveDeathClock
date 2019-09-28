package com.android.jay.livedeathclock.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.android.jay.livedeathclock.LIVE_YEARS
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.interfaces.SplashUpDateUIListener
import com.android.jay.livedeathclock.utils.DateUtil
import com.android.jay.livedeathclock.utils.SpUtils
import com.android.jay.livedeathclock.utils.Utils
import com.wx.wheelview.adapter.ArrayWheelAdapter
import com.wx.wheelview.widget.WheelView
import kotlinx.android.synthetic.main.splash_live_layout.*

/**
 * @author Mr.Liu
 * */
class SplashLiveDialog(context: Context) : Dialog(context) {

    var splashLiveWheel: WheelView<String>? = null
    var mSplashUpDateUIListener: SplashUpDateUIListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_live_layout)
        this.setCanceledOnTouchOutside(false)

        val liveYears = SpUtils.getInt(context, LIVE_YEARS, 90)

        splashLiveWheel = findViewById<WheelView<String>>(R.id.splash_live_wheel)

        splashLiveWheel?.setWheelAdapter(ArrayWheelAdapter(context))
        splashLiveWheel?.setWheelData(DateUtil.getLiveYears())
        splashLiveWheel?.setWheelSize(5)
        splashLiveWheel?.selection = liveYears ?: 90

        var style = WheelView.WheelViewStyle()
        style.selectedTextSize = Utils.dp2Px(context,10f).toInt()
        style.selectedTextZoom = 2f
        splashLiveWheel?.style = style

        setListener()

    }

    private fun setListener() {

        splash_live_over.setOnClickListener { view ->
            mSplashUpDateUIListener?.splashUpdate("${splashLiveWheel?.selectionItem}")
            this.dismiss()
        }

        splash_live_down.setOnClickListener { view ->
            this.dismiss()
        }

    }

    fun setSplashUpDateUIListener(splashUpDateUIListener: SplashUpDateUIListener){
        mSplashUpDateUIListener = splashUpDateUIListener
    }

    fun showDialog(){
        this.show()
    }

}