package com.android.jay.livedeathclock.view.viewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.adapter.FragmentLeftAdapter
import com.android.jay.livedeathclock.interfaces.RvItemClickListener
import com.android.jay.livedeathclock.utils.Utils
import kotlinx.android.synthetic.main.fragment_live_death_layout.view.*

/**
 * @author Mr.Liu
 * */
class LiveDeathTitleView : FrameLayout{

    var mContext: Context? = null
    var titleView: View? = null
    var fragmentLeftRv: RecyclerView? = null

    constructor(context: Context):this(context,null)
    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):super(context,attrs,defStyleAttr){

        mContext = context
        titleView = LayoutInflater.from(context).inflate(R.layout.fragment_live_death_layout, this, true)

        initView()

        setListener()
    }

    /**
     * 初始化控件
     * */
    private fun initView() {

        fragmentLeftRv = titleView?.findViewById(R.id.fragment_left_rv)
        val manager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        fragmentLeftRv?.layoutManager = manager
        fragmentLeftRv?.adapter = FragmentLeftAdapter(mContext!!,Utils.initDrawLayoutDate(context))
    }


    private fun setListener() {

        //菜单按钮
        fragment_live_death_menu.setOnClickListener {view ->
            fragment_live_death_dl.openDrawer(Gravity.LEFT)
        }

        //侧拉栏条目的点击事件
        (fragmentLeftRv?.adapter as(FragmentLeftAdapter)).setRvItemClickListener(object: RvItemClickListener {
            override fun itemClick(view: View, position: Int) {


            }

        })


    }

    /**
     * 添加布局
     *
     * @param view 动态添加的布局
     * */
    fun addViews(view: View){

        fragment_content_fl.removeAllViews()
        fragment_content_fl.addView(view)
    }

    /**
     * 设置主界面背景
     *
     * @param resId 资源id
     * */
    fun setBackground(resId: Int){
        fragment_live_death_dl.setBackgroundResource(resId)
    }

    /**
     * 设置菜单按钮背景
     *
     * @param resId 图片资源
     * */
    fun setMenuRes(resId: Int){
        fragment_live_death_menu.setImageResource(resId)
    }

    /**
     * 设置界面标题
     *
     * @param resId 字符串资源id
     * */
    fun setTitle(resId: Int){
        fragment_live_death_title.text = resources.getString(resId)
    }

    /**
     * 设置标题颜色
     *
     * @param textColorId 字符串字体颜色
     * */
    fun setTitleColor(textColorId: Int){
        fragment_live_death_title.setTextColor(textColorId)
    }
}