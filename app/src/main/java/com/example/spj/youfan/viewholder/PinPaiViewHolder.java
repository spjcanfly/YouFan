package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.ShouYe;
import com.example.spj.youfan.bean.ShouYeModuleData;

/**
 * Created by spj on 2016/9/30.
 * 品牌福利优享
 */
public class PinPaiViewHolder extends BaseRecyviewViewHolder{

    private final ImageView iv_pinpai;
    private final Context mContext;

    public PinPaiViewHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;
        iv_pinpai = (ImageView) itemView.findViewById(R.id.iv_pinpai);
    }

    @Override
    public void setData(ShouYe.DataBean.ModuleBean moduleBean) {

        ShouYeModuleData data = moduleBean.getData().get(0);
        String img = data.getImg();
        Glide.with(mContext).load(img)
                .placeholder(R.drawable.ic_error_page)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_pinpai);
    }
}
