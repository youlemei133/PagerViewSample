package com.hudawei.pagerviewsample;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hudawei.pagerviewsample.banner.SimpleBannerAdapter;

import java.util.List;

/**
 * Created by hudawei on 2017/2/16.
 *
 */

public class MyBannerAdapter extends SimpleBannerAdapter<Integer> {
    public MyBannerAdapter(LayoutInflater inflater, @NonNull List<Integer> datas) {
        super(inflater, datas, R.layout.viewpager_item);
    }
    @Override
    public void initView(View pageView, int position, Integer data) {
        ImageView imageView= (ImageView)pageView.findViewById(R.id.imageView);
        TextView textView= (TextView) pageView.findViewById(R.id.textView);
        imageView.setImageResource(data);
        textView.setText("图片："+(position));
    }
}
