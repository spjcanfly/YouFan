package com.example.spj.youfan.pager.detailpager;

import android.content.Context;
import android.view.View;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseSortViewPager;

/**
 * Created by spj on 2016/10/7.
 */
public class SortDongTaiDetailPager extends BaseSortViewPager{

    public SortDongTaiDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.sort_dongtai_pager, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
