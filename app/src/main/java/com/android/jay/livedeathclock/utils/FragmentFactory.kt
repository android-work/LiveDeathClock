package com.android.jay.livedeathclock.utils

import com.android.jay.livedeathclock.view.fragment.DateCalculationFragment
import com.android.jay.livedeathclock.view.fragment.DeathFragment
import com.android.jay.livedeathclock.view.fragment.LiveFragment
import com.android.jay.livedeathclock.view.fragment.WishFragment
import com.android.jay.livedeathclock.view.fragment.base.BaseFragment

var fragments: MutableMap<Int, BaseFragment> = mutableMapOf()

/**
 * 创建fragment的工具类
 * */
fun createFragment(position: Int): BaseFragment? {

    var fragment: BaseFragment? = null

    if (fragments.isEmpty() || fragments[position] == null){

        when(position){
            0 -> fragments[position] = LiveFragment()
            1 -> fragments[position] = DeathFragment()
            2 -> fragments[position] = WishFragment()
            else -> fragments[position] = DateCalculationFragment()
        }
    }

    return fragments[position]
}