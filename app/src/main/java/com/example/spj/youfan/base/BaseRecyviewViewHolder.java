package com.example.spj.youfan.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.spj.youfan.bean.shouye.ShouYe;

/**
 * Created by spj on 2016/9/29.
 */
public abstract class BaseRecyviewViewHolder extends RecyclerView.ViewHolder{
    public Context context;

    public BaseRecyviewViewHolder(Context context,View itemView) {
        super(itemView);

        this.context = context;
    }

    public abstract void setData(ShouYe.DataBean.ModuleBean moduleBean);

}
