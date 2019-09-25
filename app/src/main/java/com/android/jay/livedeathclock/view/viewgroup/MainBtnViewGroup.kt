package com.android.jay.livedeathclock.view.viewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.interfaces.MainBtnClickListener
import kotlinx.android.synthetic.main.main_btn_layout.view.*

class MainBtnViewGroup: FrameLayout{

    var mMainBtnClickListener: MainBtnClickListener? = null

    constructor(context: Context):this(context,null)
    constructor(context: Context,att:AttributeSet?):this(context,att,0)
    constructor(context: Context,att:AttributeSet?,defStyleAttr: Int):super(context,att,defStyleAttr){

        LayoutInflater.from(context).inflate(R.layout.main_btn_layout, this, true)

        setListener()
    }

    private fun setListener() {

        //生
        main_live.setOnClickListener{ view ->

            setIcRes(R.mipmap.ic_live_select,R.mipmap.ic_death,R.mipmap.ic_wish,R.mipmap.ic_date_calculation)
            mMainBtnClickListener?.btnClick(view)
        }

        //死
        main_death.setOnClickListener{ view ->

            setIcRes(R.mipmap.ic_live,R.mipmap.ic_death_select,R.mipmap.ic_wish,R.mipmap.ic_date_calculation)
            mMainBtnClickListener?.btnClick(view)
        }

        //心愿
        main_wish.setOnClickListener { view ->

            setIcRes(R.mipmap.ic_live,R.mipmap.ic_death,R.mipmap.ic_wish_select,R.mipmap.ic_date_calculation)
            mMainBtnClickListener?.btnClick(view)
        }

        //日期计算
        main_date_calculate.setOnClickListener { view ->

            setIcRes(R.mipmap.ic_live,R.mipmap.ic_death,R.mipmap.ic_wish,R.mipmap.ic_date_calculation_select)
            mMainBtnClickListener?.btnClick(view)
        }
    }

    /**
     * 设置点击按钮背景的切换
     * */
    private fun setIcRes(liveRes: Int,deathRes: Int,wishRes: Int,dateCalculateRes: Int) {
        ic_live.setBackgroundResource(liveRes)
        ic_death.setBackgroundResource(deathRes)
        ic_wish.setBackgroundResource(wishRes)
        ic_date_calculation.setBackgroundResource(dateCalculateRes)
    }

    /**
     * 设置main的四个按钮点击事件的回调
     * */
    fun setMainBtnClickListener(mainBtnClickListener: MainBtnClickListener){
        mMainBtnClickListener = mainBtnClickListener
    }
}