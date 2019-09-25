package com.android.jay.livedeathclock.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.bean.DrawLayoutBean
import com.android.jay.livedeathclock.interfaces.RvItemClickListener

/**
 * @author Mr.Liu
 * */
class FragmentLeftAdapter(var context: Context,var dates: MutableList<DrawLayoutBean>): RecyclerView.Adapter<FragmentLeftAdapter.ViewHolder>() {

    var mRvItemClickListener: RvItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.fragment_rv_item_layout, null)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = dates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemIc.setBackgroundResource(dates[position].resId)

        holder.itemText.text = dates[position].content

        holder.itemLl.setOnClickListener {view ->

            mRvItemClickListener?.itemClick(view,position)
        }

    }

    /**
     * 自定义viewholder对象
     *
     * @param view 条目布局对象
     * */
    class ViewHolder(var view: View): RecyclerView.ViewHolder(view){
        var itemIc: AppCompatImageView = view.findViewById(R.id.fragment_left_item_ic)

        var itemText: AppCompatTextView = view.findViewById(R.id.fragment_left_item_text)

        var itemLl: LinearLayout = view.findViewById(R.id.fragment_left_item_ll)
    }

    /**
     * 设置条目点击事件
     *
     * @param rvItemClickListener 点击接口对象
     * */
    fun setRvItemClickListener(rvItemClickListener: RvItemClickListener){
        mRvItemClickListener = rvItemClickListener
    }
}