package com.example.spj.youfan.pager;

import android.content.Context;
import android.view.View;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BasePager;

/**
 * Created by spj on 2016/9/28.
 */
public class MePager extends BasePager{

    public MePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {

        //把这个放在这里，防止空指针
        View view = View.inflate(mContext, R.layout.my_pager, null);

        //把子视图添加到BasePager上的Fragment上
        flContent.addView(view);
        super.initData();
    }

    @Override
    public void initTitle() {
        ibTopMenu.setVisibility(View.GONE);
        ibTopSet.setVisibility(View.VISIBLE);
        ivTopSeacher.setVisibility(View.GONE);
        ivTopMail.setVisibility(View.VISIBLE);
        ivTopBack.setVisibility(View.GONE);
        tvTop.setText("我的");
    }

    @Override
    public void initListener() {

    }
}
