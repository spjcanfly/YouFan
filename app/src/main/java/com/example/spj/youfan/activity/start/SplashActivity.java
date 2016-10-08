package com.example.spj.youfan.activity.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.spj.youfan.R;
import com.example.spj.youfan.utils.CacheUtils;

/**
 * 点击app后进入的页面
 */
public class SplashActivity extends Activity {

    private Handler handler = new Handler();
    public static final String START_First = "start_first";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否是第一次进入
                boolean isFisrt = CacheUtils.getBoolean(SplashActivity.this,START_First);
                if(isFisrt) {
                    //是第一次进入
                    intent = new Intent(SplashActivity.this,  GuideActivity.class);
                }else {
                    //不是第一次进入
                    //进入选择男生，女生，生活的页面
                    intent = new Intent(SplashActivity.this, ChoiceActivity.class);
                }
                startActivity(intent);
                // 关闭页面
                finish();
            }
        },3000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
