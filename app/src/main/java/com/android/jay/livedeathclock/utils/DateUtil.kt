package com.android.jay.livedeathclock.utils

import java.util.*

/**
 * @author Mr.Liu
 *
 * 计算wheelview的数据
 * */
object DateUtil {

    /**
     * 计算出生年份
     * */
    fun getBirYear(): MutableList<String>{

        var yearList: MutableList<String> = mutableListOf()
        var year = Calendar.getInstance().get(Calendar.YEAR)

        for (i in 100 downTo 0){
            yearList.add("${year - i}")
        }
        return yearList
    }

    /**
     * 计算出生月份
     *
     * @param year 选中年份
     * */
    fun getBirMonth(year: Int): MutableList<String>{

        var monthList: MutableList<String> = mutableListOf()
        var month = Calendar.getInstance().get(Calendar.MONTH) + 1
        var curYear = Calendar.getInstance().get(Calendar.YEAR)

        if(year < curYear){
            month = 12
        }

        monthList.clear()
        for (i in 1..month){
            monthList.add("$i")
        }
        return monthList
    }

    /**
     * 几号出生的
     *
     * @param month 选中月份
     * */
    fun getBirDate(year: Int,month: Int): MutableList<String>{

        var dateList: MutableList<String> = mutableListOf()
        var curMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        var curDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        var curYear = Calendar.getInstance().get(Calendar.YEAR)

        //获取选中月份有多少天
        var monthOfDays = getMonthOfDays(year, month)

        monthOfDays = if(year < curYear){
            monthOfDays
        }else{
            if(month == curMonth){
                curDate
            }else{
                monthOfDays
            }
        }

        for (i in 1 .. monthOfDays){
            dateList.add("$i")
        }

        return dateList
    }

    /**
     * 获取该月份有多少天
     *
     * @param year 查询该年是否为闰年
     *
     * @param month 查询的月份
     * */
    fun getMonthOfDays(year: Int,month: Int): Int{

        var days = isLeapYear(year)

        days = when(month){
            1,3,5,7,8,10,12 -> 31
            4,6,9,11 -> 30
            else -> days
        }

        return days
    }

    /**
     * 判断查询年是否为闰年,返回2月天数
     *
     * @param year 需要查询的年份
     * */
    fun isLeapYear(year: Int): Int{

        return if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)){
                    29
                }else{
                    28
                }
    }

    /**
     * 获取小时
     * */
    fun getBirHour(): MutableList<String>{

        var hourList: MutableList<String> = mutableListOf()

        for (i in 0 .. 23){
            hourList.add(String.format("%02d",i))
        }

        return hourList
    }

    /**
     * 获取分钟和秒
     * */
    fun getBirMinOrSec(): MutableList<String>{

        var minOrSecList: MutableList<String> = mutableListOf()

        for(i in 0..59){
            minOrSecList.add(String.format("%02d",i))
        }

        return minOrSecList
    }

    /**
     * 数字字符串转整型
     * */
    fun numStr2Int(numStr: String): Int{
        return numStr.toInt()
    }

    /**
     * 获取用户预选生命年限
     * */
    fun getLiveYears(): MutableList<String>{

        var liveYears: MutableList<String> = mutableListOf()
        for (i in 0..150){
            liveYears.add("$i")
        }

        return liveYears
    }
}