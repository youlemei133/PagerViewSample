package com.hudawei.pagerviewsample;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by hudawei on 2017/2/15.
 *
 */

public class SimpleTransformer implements ViewPager.PageTransformer {
    private final static float MAX_SCALE=1F;
    private final static float MIN_SCALE=0.8F;
    @Override
    public void transformPage(View page, float position) {
        Log.e("SimpleTransformer",page.getTag()+" position:"+position);
        if(position==0){//当前页面
            page.setScaleY(MAX_SCALE);
        }else if(position>=-1&&position<=1){//左边页面
            float scale=MAX_SCALE-(MAX_SCALE-MIN_SCALE)*Math.abs(position);
            page.setScaleY(scale);
        }
    }
}
