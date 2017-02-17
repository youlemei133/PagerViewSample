package com.hudawei.pagerviewsample.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by hudawei on 2017/2/17.
 * 点形：
 * 大小
 * 间距
 * 个数
 * 固定点布局
 * 移动点布局
 * 移动点位置
 */

public class BannerIndicator extends LinearLayout{
    private int mFixedItemRes;
    private int mMoveItemRes;
    private int mCount;
    private int mItemWidth;
    private int mItemHeight;
    private int mItemLeft;
    private int mItemRight;
    private int mItemTop;
    private int mItemBottom;
    private LayoutInflater mInflater;
    public BannerIndicator(Context context) {
        this(context,null);
    }

    public BannerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater=LayoutInflater.from(context);
    }

    public BannerIndicator fixed(@NonNull int fixedRes){
        mFixedItemRes=fixedRes;
        return this;
    }
    public BannerIndicator move(int moveRes){
        mMoveItemRes=moveRes;
        return this;
    }
    public BannerIndicator size(int itemWidth,int itemHeight){
        mItemWidth=itemWidth;
        mItemHeight=itemHeight;
        return this;
    }
    public BannerIndicator margin(@NonNull int... margins){
        switch (margins.length){
            case 1:
                mItemLeft=margins[0];
                mItemTop=margins[0];
                mItemRight=margins[0];
                mItemBottom=margins[0];
                break;
            case 2:
                mItemLeft=margins[0];
                mItemTop=margins[1];
                mItemRight=margins[0];
                mItemBottom=margins[1];
                break;
            case 3:
                mItemLeft=margins[0];
                mItemTop=margins[1];
                mItemRight=margins[2];
                mItemBottom=margins[1];
                break;
            case 4:
                mItemLeft=margins[0];
                mItemTop=margins[1];
                mItemRight=margins[2];
                mItemBottom=margins[3];
                break;
            default:
                throw new IllegalArgumentException("设置margin的个数范围 1~4。当前个数:"+margins.length);
        }
        return this;
    }
    public BannerIndicator count(int count){
        if(count<2)
            throw new IllegalArgumentException("指示器个数必须在2个以上 ："+count);
        mCount=count;
        return this;
    }
    public BannerIndicator build(){
        if(mCount>1){
            for(int i=0;i<mCount;i++){
                View view=inflate(mFixedItemRes);
                LayoutParams params=setItemLayoutParams();
                view.setLayoutParams(params);
                addView(view);
            }
        }
        return this;
    }


    private LayoutParams setItemLayoutParams() {
        LayoutParams params=new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        if(mItemWidth!=0)
            params.width=mItemWidth;
        if(mItemHeight!=0)
            params.height=mItemHeight;
        params.leftMargin=mItemLeft;
        params.rightMargin=mItemRight;
        params.topMargin=mItemTop;
        params.bottomMargin=mItemBottom;
        return params;
    }

    private View inflate(int layoutRes){
        return mInflater.inflate(layoutRes,null);
    }
}
