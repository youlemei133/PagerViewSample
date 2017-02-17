package com.hudawei.pagerviewsample.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by hudawei on 2017/2/17.
 * 轮播图ViewPager
 * 1.无限轮播
 * 2.手动滑动
 * 3.自动滑动
 */

public class BannerViewPager extends ViewPager implements BaseTimer.Callback,ViewPager.OnPageChangeListener {
    private BaseTimer mTimer;   //定时器
    private BaseBannerAdapter mAdapter; //适配器
    private long mDelayMillions; //定时器时间间隔
    private boolean mAutoScrollFlag;    //是否自动轮播
    public final static int STATE_PAUSE = 1;    //当Activity在onPause时，设为此状态
    public final static int STATE_RESUME = 0;   //当Activity在onResume时，设为此状态

    public BannerViewPager(Context context) {
        this(context, null);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDelayMillions = 4000;
    }

    /**
     * 设置Adapter
     * @param adapter   BaseBannerAdapter
     * @return  当前ViewPager对象
     */
    public BannerViewPager adapter(@NonNull BaseBannerAdapter adapter) {
        mAdapter = adapter;
        return this;
    }

    /**
     * 是否开启自动轮播
     * @param autoScrollFlag    是否自动轮播
     * @param delayMillions     轮播时间间隔
     * @return  当前ViewPager对象
     */
    public BannerViewPager auto(boolean autoScrollFlag, long delayMillions) {
        mDelayMillions = delayMillions;
        mAutoScrollFlag = autoScrollFlag;
        //创建一个计时器
        if(mTimer==null)
            mTimer = new BaseTimer(new Handler(Looper.getMainLooper()), mDelayMillions, this);
        return this;
    }

    /**
     * 设置 Adapter，设置当前页为第2页，开启计时器
     */
    public void build() {
        setAdapter(mAdapter);
        setCurrentItem(1);
//        addOnPageChangeListener(this);
        if (mAutoScrollFlag)
            mTimer.start();
    }

    /**
     * 在Activity里面的onPause和onResume方法中调用
     * 此方法是为了在设置了自动轮播下，根据Activity的生命周期决定开启或关闭计时器
     * @param state STATE_RESUME对应Activity里面的onResume，STATE_PAUSE对应Activity里面的onPause
     */
    public void setState(int state) {
        if (mAutoScrollFlag) {
            switch (state) {
                case STATE_RESUME:
                    if (!mTimer.isStart())
                        mTimer.start(); //开启计时器
                    break;
                case STATE_PAUSE:
                    if (mTimer.isStart())
                        mTimer.interrupt(); //关闭计时器
                    break;
                default:
                    throw new IllegalArgumentException("state 取值为STATE_RESUME、STATE_PAUSE");
            }
        }
    }

    /**
     * 时间间隔后的回调，在该方法中设置滑动到下一页
     */
    @Override
    public void onTime() {
        int curIndex = getCurrentItem();
        setCurrentItem(++curIndex);
    }

    /**
     * 处理触摸事件：
     * A.当用户手指触摸ViewPager的时候，停止计时
     * B.当用户手指离开ViewPager的时候，开始计时
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setState(STATE_PAUSE);
                break;
            case MotionEvent.ACTION_UP:
                setState(STATE_RESUME);
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position,positionOffset,positionOffsetPixels);
        Log.e("OnPageChangeListener","onPageScrolled \tposition="+position+
                "\tpositionOffset = "+positionOffset+
                "\tpositionOffsetPixels = "+positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("OnPageChangeListener","onPageSelected ="+position);
    }
    @Override
    public void onPageScrollStateChanged(int state) {
        Log.e("OnPageChangeListener","onPageScrollStateChanged = "+state);
    }
}
