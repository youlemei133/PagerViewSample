package com.hudawei.pagerviewsample.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudawei on 2017/2/16.
 *  该适配器实现无限循环，在使用的时候我们继承他的子类SimpleBannerAdapter
 */

public abstract class BaseBannerAdapter<E> extends PagerAdapter {
    private List<View> mItems;  //放入ViewPager中的Page
    private List<E> mDatas;     //初始化Page的数据集合
    private LayoutInflater mInflater;   //布局加载器

    /**
     * 根据传入的填充数据，创建页面
     * 为了实现无限循环：
     * 实际第一个位置显示的是我们看到的最后一个页面，
     * 而实际最后一个页面对应我们看到的第一个页面
     * @param inflater  布局加载器
     * @param datas 填充数据集合
     * @param pageLayout    需要显示的布局资源
     */
    public BaseBannerAdapter(LayoutInflater inflater, @NonNull List<E> datas, int pageLayout){
        mItems=new ArrayList<>();
        mInflater=inflater;
        if(datas.size()>1){//至少2个页面，才有意义
            mDatas=new ArrayList<>();
            //对传入的数据进行处理
            mDatas.add(datas.get(datas.size()-1));
            mDatas.addAll(datas);
            mDatas.add(datas.get(0));
            //加载View,这里没有填充数据
            for(int i=0;i<mDatas.size();i++){
                View view=mInflater.inflate(pageLayout,null);
                mItems.add(view);
            }
        }else{
            throw new ArrayStoreException("BaseBannerAdapter 中，传入的datas数据源至少需要2条");
        }
    }

    public BaseBannerAdapter(@NonNull List<View> items, @NonNull List<E> datas){
        mItems=items;
        mDatas=datas;
    }

    @Override
    public int getCount() {
        return mItems==null?0:mItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=mItems.get(position);
        //这里第一个位置对应了实际的最后一页
        if(position==0)
            position=mItems.size()-2;
        //这里的最后一页对应了实际的第一页
        if (position==mItems.size()-1)
            position=1;
        //初始化页面
        initView(view,position,mDatas.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mItems.get(position));
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
        ViewPager viewPager= (ViewPager) container;
        int curIndex=viewPager.getCurrentItem();
        if(curIndex==0){
            viewPager.setCurrentItem(mItems.size()-2,false);
        }else if(curIndex==mItems.size()-1){
            viewPager.setCurrentItem(1,false);
        }
    }

    /**
     * 子类需实现该接口
     * @param pageView
     * @param position
     * @param data
     */
    public abstract void initView(View pageView,int position, E data);


}
