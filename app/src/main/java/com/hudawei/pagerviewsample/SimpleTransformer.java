package com.hudawei.pagerviewsample;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by hudawei on 2017/2/15.
 *
 */

public class SimpleTransformer implements ViewPager.PageTransformer {
    private final static float MAX_SCALE=0.8F;
    private final static float MIN_SCALE=0.6F;
    @Override
    public void transformPage(View page, float position) {
        if(position==0){//当前页面
            page.setScaleX(MAX_SCALE);
            page.setScaleY(MAX_SCALE);
        }else if(position>=-1&&position<=1){//左边页面
            float scale=MAX_SCALE-(MAX_SCALE-MIN_SCALE)*Math.abs(position);
            page.setScaleX(scale);
            page.setScaleY(scale);
            page.setTranslationX(80*Math.abs(position));
        }
    }
}
