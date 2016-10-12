package com.example.spj.youfan.fragment.shopfragment;

import android.view.View;
import android.widget.TextView;

import com.example.spj.youfan.base.BaseFragment;
import com.example.spj.youfan.utils.LogUtil;

/**
 * Created by spj on 2016/10/11.
 */
public class FragmentSay extends BaseFragment{

    @Override
    public View initView() {
        TextView tv = new TextView(mContext);
        tv.setText("我是评价页面");
        LogUtil.e("mcontext" + mContext);
        LogUtil.e("woshi pinjiaa a cao nia");
        return tv;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
