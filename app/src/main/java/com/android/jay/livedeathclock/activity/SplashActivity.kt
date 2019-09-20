package com.android.jay.livedeathclock.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.android.jay.livedeathclock.IS_SHOW_SPLASH
import com.android.jay.livedeathclock.MainActivity
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.imp.SplashPresenter
import com.android.jay.livedeathclock.interfaces.presenter.IPresenter
import com.android.jay.livedeathclock.interfaces.view.IView
import com.android.jay.livedeathclock.utils.SpUtils
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @author Mr.Liu
 * */
class SplashActivity : AppCompatActivity(),IView.ISplashView{

    var mSplashPresenter: IPresenter.ISplashPresenter? = null
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash)

        context = this

        mSplashPresenter = SplashPresenter(applicationContext)

        mSplashPresenter?.bindView(this)

        mSplashPresenter?.isShowSplash()


    }

    /**
     * 是否展示欢迎页ui
     * */
    override fun callBackIsShowSplash(isShow: Boolean) {

        if(isShow){
            createAnimator()
            SpUtils.saveBoolean(this, IS_SHOW_SPLASH,false)

            splash_over.text = getString(R.string.splash_popup_over)

            splash_over.setOnClickListener { view ->
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
        }else{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    private fun createAnimator() {

        val animation = AlphaAnimation(1f, 0f)
        animation.duration = 3000
        animation.fillAfter = true

        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(p0: Animation?) {
                splash_bg.visibility = View.GONE
            }

            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })

        splash_bg?.startAnimation(animation)
    }

    override fun onStop() {
        super.onStop()
        splash_clock.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mSplashPresenter?.unBindView()
    }
}