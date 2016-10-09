package com.example.spj.youfan.pager.detailpager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by spj on 2016/10/9.
 */
public class BrandDetailPager {
    private final Context mContext;
    private final String mName;
    public View rootView;

    public BrandDetailPager(Context context, String name) {
        this.mContext = context;
        this.mName = name;
        rootView = initView();
    }

    private View initView() {
        TextView tv = new TextView(mContext);
        tv.setText(mName);
        return tv;
    }

    public void initData() {

    }
}
