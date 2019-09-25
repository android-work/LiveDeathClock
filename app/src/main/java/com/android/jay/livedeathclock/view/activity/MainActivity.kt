package com.android.jay.livedeathclock.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentTransaction
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.interfaces.MainBtnClickListener
import com.android.jay.livedeathclock.utils.createFragment
import com.android.jay.livedeathclock.utils.logUtil
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

                var fragmentTag: String? = null

                //创建/获取fragment
                var fragment = when(view.id){

                    R.id.main_live -> {

                        fragmentTag = "live"
                        createFragment(0)
                    }
                    R.id.main_death ->{

                        fragmentTag = "death"
                        createFragment(1)
                    }
                    R.id.main_wish ->{

                        fragmentTag = "wish"
                        createFragment(2)
                    }else -> {

                        fragmentTag = "dateCalculate"
                        createFragment(3)
                    }

                }

                //将fragment添加到布局中
                beginTransaction?.hide(curFragment!!)

                logUtil("tag","${fragment?.isAdded}")
                if(fragment?.isAdded ?: false/*&& supportFragmentManager.findFragmentByTag(fragmentTag) != null*/){

                    logUtil("tag","已经加载过")
                    beginTransaction?.show(fragment!!)
                }else{

                    logUtil("tag","${curFragment}:fragmentTag：$fragment")
                    beginTransaction?.add(R.id.main_fl,fragment!!)
                    beginTransaction?.commit()
                }
                curFragment = fragment

            }

        })

    }
}
