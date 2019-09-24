package com.android.jay.livedeathclock.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentTransaction
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.interfaces.MainBtnClickListener
import com.android.jay.livedeathclock.utils.createFragment
import com.android.jay.livedeathclock.view.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Mr.Liu
 * */
class MainActivity : AppCompatActivity() {

    var curFragment: BaseFragment? = null
    var beginTransaction: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        curFragment = createFragment(0)

        beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction?.add(R.id.main_fl,curFragment!!)
        beginTransaction?.commit()

        setListener()

    }

    private fun setListener() {

        //按钮的点击事件回调
        main_btn_group.setMainBtnClickListener(object : MainBtnClickListener{
            override fun btnClick(view: View) {

                var fragment: BaseFragment? = null

                //创建/获取fragment
                fragment = when(view.id){

                    R.id.main_live -> {
                        createFragment(0)
                    }
                    R.id.main_death ->{
                        createFragment(1)
                    }
                    R.id.main_wish ->{
                        createFragment(2)
                    }else -> {
                        createFragment(3)
                    }

                }

                //将fragment添加到布局中
                beginTransaction?.hide(curFragment!!)
                if(fragment?.isAdded == false){
                    beginTransaction?.show(fragment)
                }else{
                    beginTransaction?.add(R.id.main_fl,fragment!!)?.commit()
                    curFragment = fragment
                }

            }

        })

    }
}
