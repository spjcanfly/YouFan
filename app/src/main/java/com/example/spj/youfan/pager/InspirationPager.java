package com.example.spj.youfan.pager;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.spj.youfan.base.BasePager;

/**
 * Created by spj on 2016/9/28.
 */
public class InspirationPager extends BasePager{

    public InspirationPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();


        TextView tv = new TextView(mContext);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        //把子视图添加到BasePager上的Fragment上
        flContent.addView(tv);
        tv.setText("灵感");
    }

    @Override
    public void initTitle() {
        ibTopMenu.setVisibility(View.GONE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.VISIBLE);
        ivTopMail.setVisibility(View.GONE);
        tvTop.setText("资讯");
    }
}
