package com.android.jay.livedeathclock.utils

import android.content.Context
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.bean.DrawLayoutBean

/**
 * @author Mr.Liu
 * */
object Utlis{

    /**
     * dp转px工具类
     * */
    fun dp2Px(context: Context,dp: Float): Float{

        val density = context.resources.displayMetrics.density
        return (dp * density + 0.5f)
    }

    /**
     * 初始化侧拉栏数据
     * */
    fun initDrawLayoutDate(context: Context): MutableList<DrawLayoutBean>{
        var icRes = intArrayOf(R.mipmap.ic_clock,R.mipmap.ic_update_bir,R.mipmap.ic_death,R.mipmap.ic_help,R.mipmap.ic_issue_back,R.mipmap.ic_score)
        var textRes = intArrayOf(R.string.clock,R.string.update_bir,R.string.death,R.string.help,R.string.issue_back,R.string.score)
        val drawLayoutBean = DrawLayoutBean()
        var dates: MutableList<DrawLayoutBean> = mutableListOf()

        for (i in 0 until icRes.size){
            drawLayoutBean.content = context.resources.getString(textRes[i])
            drawLayoutBean.resId = icRes[i]
            dates.add(drawLayoutBean)
        }

        return dates
    }
}