package com.example.spj.youfan.pager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.user.LoginActivity;
import com.example.spj.youfan.base.BasePager;

/**
 * Created by spj on 2016/9/28.
 * 完美兼容的scrollview
 */
public class MePager extends BasePager {


    private ImageView iv_user;
    private ImageView iv_log;
    private TextView tv_login_now;
    private TextView tv_my_xinyuan;
    private TextView tv_my_collect;
    private TextView tv_my_zuji;
    private LinearLayout ll_dingdan;
    private TextView tv_dai_fukuan;
    private TextView tv_dai_fahuo;
    private TextView tv_dai_shouhuo;
    private TextView tv_dai_pingjia;
    private TextView tv_shouhou;
    private TextView tv_dizhi_guanli;
    private TextView tv_my_huiyuan;
    private TextView tv_my_fanpiao;
    private TextView tv_online_server;
    private TextView tv_idea_response;
    private TextView tv_ask_win;

    public MePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        View view = findViews();
        //把子视图添加到BasePager上的Fragment上
        if(flContent != null) {
            flContent.removeAllViews();
        }
        flContent.addView(view);
        super.initData();

    }

    private View findViews() {
        //把这个放在这里，防止空指针
        View view = View.inflate(mContext, R.layout.my_pager, null);
        iv_user = (ImageView) view.findViewById(R.id.iv_user);
        iv_log = (ImageView) view.findViewById(R.id.iv_log);
        tv_login_now = (TextView) view.findViewById(R.id.tv_login_now);
        tv_my_xinyuan = (TextView) view.findViewById(R.id.tv_my_xinyuan);
        tv_my_collect = (TextView) view.findViewById(R.id.tv_my_collect);
        tv_my_zuji = (TextView) view.findViewById(R.id.tv_my_zuji);
        ll_dingdan = (LinearLayout) view.findViewById(R.id.ll_dingdan);
        tv_dai_fukuan = (TextView) view.findViewById(R.id.tv_dai_fukuan);
        tv_dai_fahuo = (TextView) view.findViewById(R.id.tv_dai_fahuo);
        tv_dai_shouhuo = (TextView) view.findViewById(R.id.tv_dai_shouhuo);
        tv_dai_pingjia = (TextView) view.findViewById(R.id.tv_dai_pingjia);
        tv_shouhou = (TextView) view.findViewById(R.id.tv_shouhou);
        tv_dizhi_guanli = (TextView) view.findViewById(R.id.tv_dizhi_guanli);
        tv_my_huiyuan = (TextView) view.findViewById(R.id.tv_my_huiyuan);
        tv_my_fanpiao = (TextView) view.findViewById(R.id.tv_my_fanpiao);
        tv_online_server = (TextView) view.findViewById(R.id.tv_online_server);
        tv_idea_response = (TextView) view.findViewById(R.id.tv_idea_response);
        tv_ask_win = (TextView) view.findViewById(R.id.tv_ask_win);


        return view;
    }


    @Override
    public void initTitle() {
        ibTopMenu.setVisibility(View.GONE);
        ibTopSet.setVisibility(View.VISIBLE);
        ivTopSeacher.setVisibility(View.GONE);
        ivTopMail.setVisibility(View.VISIBLE);
        ivTopBack.setVisibility(View.GONE);
        tv_popwindow.setVisibility(View.GONE);
        tvTop.setVisibility(View.VISIBLE);
        tvTop.setText("我的");
        rlTitleBar.setVisibility(View.GONE);
        ll_common_title.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {

        ivTopMail.setOnClickListener(new MyOnclickListener());
        ibTopSet.setOnClickListener(new MyOnclickListener());
        iv_user.setOnClickListener(new MyOnclickListener());
        iv_log.setOnClickListener(new MyOnclickListener());
        tv_login_now.setOnClickListener(new MyOnclickListener());
        tv_my_xinyuan.setOnClickListener(new MyOnclickListener());
        tv_my_zuji.setOnClickListener(new MyOnclickListener());
        tv_my_collect.setOnClickListener(new MyOnclickListener());
        tv_dai_fahuo.setOnClickListener(new MyOnclickListener());
        tv_dai_fukuan.setOnClickListener(new MyOnclickListener());
        tv_dai_pingjia.setOnClickListener(new MyOnclickListener());
        tv_dai_shouhuo.setOnClickListener(new MyOnclickListener());
        tv_shouhou.setOnClickListener(new MyOnclickListener());
        tv_dizhi_guanli.setOnClickListener(new MyOnclickListener());
        tv_my_huiyuan.setOnClickListener(new MyOnclickListener());
        tv_my_fanpiao.setOnClickListener(new MyOnclickListener());
    }



    //跳转到登录的activity
    private void goLoginActivity() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }



    private class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            goLoginActivity();
        }
    }
}
