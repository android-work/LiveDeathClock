package com.android.jay.livedeathclock.view.fragment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.utils.logUtil

/**
 * @author Mr.Liu
 * */
abstract class BaseFragment: Fragment() {

    var mContext: Context? = null
    var mView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_base_layout, null)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        logUtil("tag","加载子布局")

        initBaseLayout(mView!!)

        initData()
    }

    /**
     * 获取布局资源
     *
     * @param view 布局
     * */
    abstract fun initBaseLayout(view: View)

    /**
     * 加载数据
     * */
    abstract fun initData()
}