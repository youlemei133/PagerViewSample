package com.hudawei.pagerviewsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processClick(View view){
        switch (view.getId()){
            case R.id.vp_adapter:
                startActivity(new Intent(this,PagerAdapterActivity.class));
                break;
            case R.id.vp_transfomer:
                startActivity(new Intent(this,TransformerActivity.class));
                break;
            case R.id.vp_banner:
                startActivity(new Intent(this,BannerActivity.class));
                break;
        }
    }
}
