package com.example.spj.youfan.pager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.user.LoginActivity;
import com.example.spj.youfan.base.BasePager;

/**
 * Created by spj on 2016/9/28.
 */
public class ShopPager extends BasePager{

    private Button btn_login;

    public ShopPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        //把这个放在这里，防止空指针
        View view = View.inflate(mContext, R.layout.shop_pager, null);

        btn_login = (Button) view.findViewById(R.id.btn_login);
        //把子视图添加到BasePager上的Fragment上
        if(flContent != null) {
            flContent.removeAllViews();
        }
        flContent.addView(view);
        super.initData();
    }

    @Override
    public void initTitle() {
        ibTopMenu.setVisibility(View.GONE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.GONE);
        ivTopMail.setVisibility(View.GONE);
        ivTopBack.setVisibility(View.GONE);
        tvTop.setText("购物袋");
    }

    //为按钮设置监听
    @Override
    public void initListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,LoginActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

}
