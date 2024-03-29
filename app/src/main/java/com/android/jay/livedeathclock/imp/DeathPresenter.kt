package com.android.jay.livedeathclock.imp

import android.content.Context
import android.os.CountDownTimer
import com.android.jay.livedeathclock.BIR_DATE
import com.android.jay.livedeathclock.CUR_DATE
import com.android.jay.livedeathclock.LIVE_YEARS
import com.android.jay.livedeathclock.R
import com.android.jay.livedeathclock.interfaces.presenter.IPresenter
import com.android.jay.livedeathclock.interfaces.view.IView
import com.android.jay.livedeathclock.receiver.SingleReceiver
import com.android.jay.livedeathclock.utils.DateUtil
import com.android.jay.livedeathclock.utils.SpUtils
import com.android.jay.livedeathclock.utils.logUtil
import java.time.Year
import java.util.*

class DeathPresenter(var context: Context): IPresenter.IDeathPresenter {

    var mDeathView: IView.IDeathView? = null
    var countdownMillis: Long = 0
    var countDown: CountDownTimer? = null

    /**
     * 计算需要绘制的格子、填充的格子
     * */
    override fun calculateDrawGeZi() {

        //获取总生命
        val totalAge = SpUtils.getString(context, LIVE_YEARS, "90")!!.toInt()
        //获取当前年龄
        val curAge = DateUtil.calculationMonths(
            DateUtil.dateStr2Millis(
                SpUtils.getString(context!!, BIR_DATE, "")!!,
                "yyyy-MM-dd HH:mm:ss"
            )
        )
        mDeathView?.callBackDrawGeZi(totalAge * 12,curAge)
    }

    /**
     * 计算余生电池
     * */
    override fun calculateReminderLive() {

        //获取当前年龄
        val curAge = DateUtil.calculationMonths(
            DateUtil.dateStr2Millis(
                SpUtils.getString(context!!, BIR_DATE, "")!!,
                "yyyy-MM-dd HH:mm:ss"
            )
        )
        //获取总生命
        val totalAge = SpUtils.getString(context, LIVE_YEARS, "90")!!.toInt()

        var x = ((totalAge * 12 - curAge) * 100 / (totalAge * 12))

        logUtil("tag","$totalAge - $curAge = ${(totalAge - curAge)*100} ---> ${(totalAge - curAge)*100 / totalAge}")

        var batteryId = when(x){
            in 0..15 ->{
                R.mipmap.ic_battery1
            }
            in 16..25 ->{
                R.mipmap.ic_battery2
            }
            in 26..35 ->{
                R.mipmap.ic_battery3
            }
            in 36..45 ->{
                R.mipmap.ic_battery4
            }
            in 46..55 ->{
                R.mipmap.ic_battery5
            }
            in 56..65 ->{
                R.mipmap.ic_battery6
            }
            in 66..75 ->{
                R.mipmap.ic_battery7
            }
            in 76..85 ->{
                R.mipmap.ic_battery8
            }
            else ->{
                R.mipmap.ic_battery9
            }

        }

        mDeathView?.callBackDrawBattery(batteryId,"$x%")
    }

    /**
     *生命倒计时
     **/
    override fun calculateSecondCountdown() {

        val endMillis = calculateDeathMillis()

        val calendar = Calendar.getInstance()
        val curMillis = calendar.timeInMillis
        countdownMillis = endMillis - curMillis
        logUtil("tag","$endMillis - $curMillis = ${countdownMillis}")

//        mDeathView?.callBackSecondCountdown(countdownMillis / 1000)

        //开启倒计时
        startCountDown()
    }

    /**
     * 获取生命结束时的毫秒值
     * */
    private fun calculateDeathMillis(): Long {
        val birDate = SpUtils.getString(context!!, BIR_DATE, "")
        val dateMillis = DateUtil.dateStr2Millis(birDate!!, "yyyy-MM-dd HH:mm:ss")

        val strYear = SpUtils.getString(context!!, LIVE_YEARS, "90")

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateMillis
        logUtil("tag", "${Calendar.YEAR}")
        calendar.add(Calendar.YEAR, strYear!!.toInt())

        //获取结束点日期毫秒值
        return calendar.timeInMillis
    }

    /**
     * 开启秒倒计时
     * */
    private fun startCountDown() {

        countDown = object: CountDownTimer(countdownMillis,1000) {
            override fun onTick(p0: Long) {

                countdownMillis = p0
                logUtil("tag","倒计时:${p0/1000}")
                //进行view的更新
                mDeathView?.callBackSecondCountdown(p0 / 1000)
            }

            override fun onFinish() {

            }

        }

        countDown?.start()
    }

    /**
     * 生命倒计时，天、月、年
     * */
    override fun calculateLiveCountdown() {

        //保存日期
        SpUtils.saveString(context, CUR_DATE, DateUtil.formatDate())

        SingleReceiver.getInstance().setBroadcastReceiverDateListener {
            calculateCountdown()
        }

        calculateCountdown()
    }

    /**
     * 计算天、月、年倒计时
     * */
    private fun calculateCountdown() {

        //生命结束毫秒值
        val endMillis = calculateDeathMillis()

        //计算天
        val days = countdownMillis / (24 * 60 * 60 * 1000)
        //计算月
        val months = DateUtil.calculationCountDownMonths(endMillis)
        //计算年
        val years = DateUtil.calculationCountDownAge(endMillis)

        logUtil("tag","$years - $months - $days")
        mDeathView?.callBackLiveCountdown(days, months, years)
    }

    /**
     * 获取数据库中存放的事件
     * */
    override fun getSqlEvents() {


    }


    /**
     * 解绑view
     * */
    override fun unBindView() {

        mDeathView = null
        //结束倒计时
        countDown?.cancel()
        countDown = null
    }

    /**
     * 绑定view
     * */
    override fun bindView(deathView: IView.IDeathView) {

        mDeathView = deathView
    }
}