package com.hudawei.pagerviewsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hudawei on 2017/2/15.
 *
 */

public class PagerFragment extends Fragment {
    private String text;
    private boolean isVisible;
    private boolean isPrepared;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        text=getArguments().getString("text");
        Log.e("PagerFragment",text+"：被创建");
        return inflater.inflate(R.layout.fragment_pager,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=(TextView)view.findViewById(R.id.textView);
        isPrepared=true;
        Log.e("PagerFragment",text+"  onViewCreated");
        lazyLoad();
    }

    /**
     * 设置当前页面可见，以及前一个后一个页面不可见
     * 优先于onViewGreated执行
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("PagerFragment",text+"  setUserVisibleHint:"+isVisibleToUser);
        //懒加载
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        Log.e("PagerFragment",text+"  lazyLoad");
        getData();//数据请求
    }

    protected void onInvisible() {
    }

    private void getData() {
        textView.setText(text);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("PagerFragment",text+"：被销毁");
    }


}
