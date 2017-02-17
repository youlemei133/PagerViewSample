package com.hudawei.pagerviewsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hudawei.pagerviewsample.banner.BannerIndicator;
import com.hudawei.pagerviewsample.banner.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudawei on 2017/2/16.
 *
 *
 */

public class BannerActivity extends AppCompatActivity {
    private BannerViewPager viewPager;
    List<Integer> resIds;
    private List<View> views;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        viewPager = (BannerViewPager) findViewById(R.id.viewPager);
        getSource();
        //创建一个SimpleBannerAdapter的子类，传入Page中需要填充的数据
        MyBannerAdapter adapter=new MyBannerAdapter(getLayoutInflater(),resIds);

        //设置ViewPager是否开启自动滑动以及适配器，通过build为ViewPager添加适配器
        viewPager.adapter(adapter)
//                .auto(true,4000)
                .build();

        BannerIndicator indicator= (BannerIndicator) findViewById(R.id.bannerIndicator);
        indicator.fixed(R.layout.banner_indicator)
                .count(resIds.size())
                .margin(10)
                .size(20,20)
                .build();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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
        });
    }

    private void getSource() {
        resIds=new ArrayList<>();
        resIds.add( R.mipmap.a);
        resIds.add( R.mipmap.b);
        resIds.add( R.mipmap.c);
        resIds.add( R.mipmap.d);
        resIds.add( R.mipmap.e);
        resIds.add( R.mipmap.f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setState(BannerViewPager.STATE_RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewPager.setState(BannerViewPager.STATE_PAUSE);
    }

}
