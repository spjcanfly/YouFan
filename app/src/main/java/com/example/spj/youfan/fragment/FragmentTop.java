package com.example.spj.youfan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseShopFragment;
import com.lzy.widget.vertical.VerticalScrollView;

/**
 * Created by spj on 2016/10/10.
 */
public class FragmentTop extends BaseShopFragment{

    private VerticalScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scrollview, container, false);
        scrollView = (VerticalScrollView) view.findViewById(R.id.scrollView);
//        TextView oldTextView = (TextView) view.findViewById(R.id.old_textview);
        //设置删除线
//        oldTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return view;
    }

    @Override
    public void goTop() {
        scrollView.goTop();
    }

}
