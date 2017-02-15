package com.hudawei.pagerviewsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudawei on 2017/2/15.
 *  position:
 *  ( - ,-1) 左边不可见的页面
 *  [-1,0) 左边可见的页面
 *  0      当前页面
 *  (0,1] 右边可见的页面
 *  (1,+) 右边不可见的页面
 */

public class TransformerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<ImageView> views;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformer);
        viewPager=(ViewPager) findViewById(R.id.viewPager);

        views=new ArrayList<>();
        int[] resId=new int[]{R.mipmap.a,R.mipmap.b,R.mipmap.c};
        for(int i=0;i<3;i++){
            ImageView imageView=new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(resId[i]);
            imageView.setTag("页面："+(i+1));
            views.add(imageView);
        }

        PagerAdapter adapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views.get(position));
                return views.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }
        };


        viewPager.setPageMargin(40);
        viewPager.setPageTransformer(false,new SimpleTransformer());
        viewPager.setAdapter(adapter);
    }

}
