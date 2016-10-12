package com.example.spj.youfan.fragment.shopfragment;

import android.view.View;
import android.widget.TextView;

import com.example.spj.youfan.base.BaseFragment;

/**
 * Created by spj on 2016/10/11.
 */
public class FragmentSingleShop extends BaseFragment {


    @Override
    public View initView() {
        TextView tv = new TextView(mContext);
        tv.setText("我是单品详情页面");
        return tv;
    }

}
