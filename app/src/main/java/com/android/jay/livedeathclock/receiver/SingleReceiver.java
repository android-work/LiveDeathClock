package com.android.jay.livedeathclock.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.android.jay.livedeathclock.utils.DateUtil;
import com.android.jay.livedeathclock.utils.SpUtils;

import static com.android.jay.livedeathclock.ContentApiKt.CUR_DATE;

public class SingleReceiver {

    private static SingleReceiver mSingleReceiver;
    private BroadcastReceiverListener broadcastReceiverListener;
    private BroadcastReceiverDateListener mBroadcastReceiverDateListener;

    public static SingleReceiver getInstance(){
        if (mSingleReceiver == null){
            mSingleReceiver = new SingleReceiver();
        }
        return mSingleReceiver;
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() .equals(Intent.ACTION_TIME_TICK)){
                if (broadcastReceiverListener!=null){
                    broadcastReceiverListener.receiver();
                }

                Log.e("tag","获取日期："+SpUtils.INSTANCE.getString(context,CUR_DATE,DateUtil.INSTANCE.formatDate()));

                //如果日期变化则更新天
                if (!(SpUtils.INSTANCE.getString(context,CUR_DATE,DateUtil.INSTANCE.formatDate()).equals(DateUtil.INSTANCE.formatDate()))){

                    //更新保存日期
                    SpUtils.INSTANCE.saveString(context,CUR_DATE, DateUtil.INSTANCE.formatDate());

                    //更新天数
                    if (mBroadcastReceiverDateListener!=null){
                        mBroadcastReceiverDateListener.updateDate();
                    }
                }
            }
        }
    };

    /**
     * 注册广播
     * */
    public void regist(Context context){
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
        context.registerReceiver(mReceiver,intentFilter);
    }

    /**
     * 取消注册
     * */
    public void unRegist(Context context){
        context.unregisterReceiver(mReceiver);
        mSingleReceiver = null;
    }

    /**
     * 创建广播回调接口
     * */
    public interface BroadcastReceiverListener{

        void receiver();
    }

    public void setBroadcastReceiverListener(BroadcastReceiverListener broadcastReceiverListener){
        this.broadcastReceiverListener = broadcastReceiverListener;
    }

    /**
     * 创建天数更新接口
     * */
    public interface BroadcastReceiverDateListener{

        void updateDate();
    }

    public void setBroadcastReceiverDateListener(BroadcastReceiverDateListener mBroadcastReceiverDateListener){

        this.mBroadcastReceiverDateListener = mBroadcastReceiverDateListener;
    }

}
