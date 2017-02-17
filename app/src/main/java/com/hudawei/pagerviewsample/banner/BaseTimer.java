package com.hudawei.pagerviewsample.banner;

import android.os.Handler;
import android.support.annotation.NonNull;

/**
 * Created by hudawei on 2017/2/17.
 * 开始计时
 * 每隔固定时间发送通知
 * 设置打断计时，重置
 * <p>
 * 每隔固定时间 处理事件  start()
 * 用户触摸屏幕，打断循环  interrupt()  ACTION_DOWN
 * 用户离开屏幕，固定时间后重启循环  start()    ACTION_UP
 */

public class BaseTimer {
    private Handler mHandler;
    private Runnable mRunnable;     //用来
    private long mDelayMillions;    //时间间隔
    private Callback mCallback;     //时间间隔后的回调接口
    private boolean mStartFlag;     //定时器是否已经开启

    /**
     *
     * @param handler   Handler
     * @param delayMillions 时间间隔
     * @param callBack  时间间隔后的回调
     */
    public BaseTimer(Handler handler, long delayMillions, @NonNull Callback callBack) {
        mHandler = handler;
        mDelayMillions = delayMillions;
        mCallback = callBack;
        //时间间隔后调用回调方法，以及开始下一个计时
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mCallback.onTime();
                mHandler.postDelayed(this, mDelayMillions);
            }
        };
    }

    /**
     * 开始计时
     */
    public void start() {
        mStartFlag=true;
        mHandler.postDelayed(mRunnable, mDelayMillions);
    }

    /**
     * 停止计时
     */
    public void interrupt() {
        mStartFlag=false;
        mHandler.removeCallbacks(mRunnable);
    }

    /**
     * 计时器是否开启
     * @return  true计时器正在及时，反之
     */
    public boolean isStart(){
        return mStartFlag;
    }

    /**
     * 时间间隔后的回调接口
     */
    public interface Callback {
        void onTime();
    }

}
