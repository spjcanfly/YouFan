package com.example.spj.youfan.pager;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.spj.youfan.activity.MainActivity;
import com.example.spj.youfan.base.BasePager;

/**
 * Created by spj on 2016/9/28.
 */
public class HomePager extends BasePager {

    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {

        TextView tv = new TextView(mContext);

        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        //把子视图添加到BasePager上的Fragment上
        flContent.addView(tv);
        tv.setText("主页");
        super.initData();
    }

    @Override
    public void initTitle() {
        ibTopMenu.setVisibility(View.VISIBLE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.VISIBLE);
        ivTopBack.setVisibility(View.GONE);
        ivTopMail.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {
        //切换侧滑
        ibTopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mContext;
                //切换左侧的开和关
                mainActivity.getSlidingMenu().toggle();
            }
        });
    }
}
