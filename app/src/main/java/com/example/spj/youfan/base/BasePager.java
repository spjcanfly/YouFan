package com.example.spj.youfan.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.MainActivity;
import com.example.spj.youfan.utils.LogUtil;

/**
 * Created by spj on 2016/8/14.
 */
public abstract class BasePager {

    public Context mContext;//MainActivity
    public View rootView;

    public ImageView ibTopMenu;
    public ImageView ibTopSet;
    public ImageView ivTopSeacher;
    public ImageView ivTopMail;
    public FrameLayout flContent;
    public TextView tvTop;

    public BasePager(Context context) {
        this.mContext = context;
        //构造执行，视图初始化
        rootView = initView();
        LogUtil.e("mcontext" + mContext);
    }

    private View initView() {

        View view = View.inflate(mContext, R.layout.basepager, null);
        ibTopMenu = (ImageView) view.findViewById(R.id.ib_top_menu);
        ibTopSet = (ImageView) view.findViewById(R.id.ib_top_set);
        ivTopMail = (ImageView) view.findViewById(R.id.iv_top_mail);
        ivTopSeacher = (ImageView) view.findViewById(R.id.iv_top_seacher);
        tvTop = (TextView) view.findViewById(R.id.tv_top);
        flContent = (FrameLayout) view.findViewById(R.id.fl_content);
        //处理titlebar的控件显示与隐藏
        initTitle();
        LogUtil.e("ibto" + ibTopMenu);

        ibTopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mContext;
                //切换左侧的开和关
                mainActivity.getSlidingMenu().toggle();
            }
        });

        return view;
    }

    /**
     * 初始化数据;当孩子需要初始化数据;或者绑定数据;联网请求数据并且绑定的时候，重写该方法
     */
    public void initData() {

    }

    public abstract void initTitle();
}
