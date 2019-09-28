package com.android.jay.livedeathclock.view.viewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.android.jay.livedeathclock.R
import kotlinx.android.synthetic.main.fragment_live_bottom_layout.view.*

/**
 * @author Mr.Liu
 * */
class LiveBottomViewGroup: FrameLayout {

    constructor(context: Context): this(context,null)
    constructor(context: Context,att: AttributeSet?): this(context,att,0)
    constructor(context: Context,att: AttributeSet?,defaultStyle: Int): super(context,att,defaultStyle){

        LayoutInflater.from(context).inflate(R.layout.fragment_live_bottom_layout, this, true)
    }

    /**
     * 设置布局背景
     *
     * @param colorRes 背景色资源
     * */
    fun setBackgroundColors(colorRes: Int){

        bottom_text_layout.setBackgroundColor(colorRes)
    }

    /**
     * 设置布局背景
     *
     * @param icRes 背景图片资源
     * */
    fun setBackgroundIcon(icRes: Int){

        bottom_text_layout.setBackgroundResource(icRes)
    }

    /**
     * 设置左上角文本内容
     * */
    fun setTopOneText(content: String){

        top_one_text.text = content
    }

    /**
     * 设置左下角文本内容
     * */
    fun setBottomOneText(content: String){

        bottom_one_text.text = content
    }

    /**
     * 设置中上文本内容
     * */
    fun setTopTwoText(content: String){

        top_two_text.text = content
    }

    /**
     * 设置中下文本内容
     * */
    fun setBottomTwoText(content: String){

        bottom_two_text.text = content
    }

    /**
     * 设置右上角文本内容
     * */
    fun setTopThreeText(content: String){

        top_three_text.text = content
    }

    /**
     * 设置右下角文本内容
     * */
    fun setBottomThreeText(content: String){

        bottom_three_text.text = content
    }


}