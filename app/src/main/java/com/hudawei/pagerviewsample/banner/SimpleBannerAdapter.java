package com.hudawei.pagerviewsample.banner;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * Created by hudawei on 2017/2/16.
 * 如果需要实现一个无限循环的适配器，继承该类
 */

public class SimpleBannerAdapter<E> extends BaseBannerAdapter<E> {
    public SimpleBannerAdapter(LayoutInflater inflater, @NonNull List<E> datas, int pageLayout) {
        super(inflater, datas, pageLayout);
    }

    public SimpleBannerAdapter(@NonNull List<View> items, @NonNull List<E> datas) {
        super(items, datas);
    }

    /**
     * 初始化View的数据，以及设置事件
     * @param pageView
     * @param position
     * @param data
     */
    @Override
    public void initView(View pageView,int position, E data) {

    }

}
