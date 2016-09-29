package com.example.spj.youfan.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.MainActivity;
import com.example.spj.youfan.base.BasePager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by spj on 2016/9/28.
 */
public class HomePager extends BasePager {

    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {

        View view = View.inflate(mContext, R.layout.home_pager, null);

        initSpinner();
        super.initData();
    }

    private void initSpinner() {
        List<String> dataset = new ArrayList<>(Arrays.asList("男生", "女生", "生活"));
        nice_spinner.setTextColor(Color.BLACK);
        nice_spinner.setPadding(0, 0, 0, 0);
        nice_spinner.setTextSize(23);
        nice_spinner.attachDataSource(dataset);
    }

    @Override
    public void initTitle() {
        ibTopMenu.setVisibility(View.VISIBLE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.VISIBLE);
        ivTopBack.setVisibility(View.GONE);
        ivTopMail.setVisibility(View.GONE);
        tvTop.setVisibility(View.GONE);
        nice_spinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {
        //切换侧滑
        ibTopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mContext;
                //切换左侧的开和关
                mainActivity.getSlidingMenu().toggle();
            }
        });
    }
}
