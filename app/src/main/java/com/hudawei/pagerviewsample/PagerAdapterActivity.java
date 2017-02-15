package com.hudawei.pagerviewsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudawei on 2017/2/15.
 */

public class PagerAdapterActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_adapter);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
    }

    public void processClick(View view){
        switch (view.getId()){
            case R.id.pagerAdapter :
                pagerAdapter();
                break;
            case R.id.pagerFragmentAdapter :
                pagerFragmentAdapter();
                break;
            case R.id.pagerFragmentStateAdapter :
                pagerFragmentStateAdapter();
                break;
        }
    }

    public void pagerAdapter(){
        final List<TextView> viewList=new ArrayList<>();
        for(int i=0;i<5;i++) {
            TextView textView = new TextView(this);
            textView.setText("页面："+(i+1));
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            viewList.add(textView);
        }

        PagerAdapter adapter=new PagerAdapter() {
            //返回page的数量
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            //将Page添加到ViewPager,container即ViewPager
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ViewPager.LayoutParams params=new ViewPager.LayoutParams();
                params.width= ViewPager.LayoutParams.MATCH_PARENT;
                params.height= ViewPager.LayoutParams.MATCH_PARENT;

                container.addView(viewList.get(position),params);
                return viewList.get(position);
            }
            //销毁Page,ViewPage移除Page
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //不要调用父类的该方法，父类是直接抛出异常
//                super.destroyItem(container,position,object);
                container.removeView(viewList.get(position));
            }
        };
        viewPager.setAdapter(adapter);
    }

    public void pagerFragmentAdapter(){
        final List<Fragment> fragments=new ArrayList<>();
        for(int i=0;i<5;i++){
            PagerFragment fragment=new PagerFragment();
            Bundle bundle=new Bundle();
            bundle.putString("text","页面："+(i+1));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(adapter);
    }

    public void pagerFragmentStateAdapter(){
        final List<Fragment> fragments=new ArrayList<>();
        for(int i=0;i<30;i++){
            PagerFragment fragment=new PagerFragment();
            Bundle bundle=new Bundle();
            bundle.putString("text","页面："+(i+1));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        FragmentStatePagerAdapter adapter=new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(adapter);
    }
}
