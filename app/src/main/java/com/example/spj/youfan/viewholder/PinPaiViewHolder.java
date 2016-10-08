package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.shouye.ShouYe;
import com.example.spj.youfan.bean.shouye.ShouYeModuleData;

/**
 * Created by spj on 2016/9/30.
 * 品牌福利优享
 */
public class PinPaiViewHolder extends BaseRecyviewViewHolder{

    private  ImageView iv_pinpai;
    private final Context mContext;
    private final View mItemView;
    private ImageView iv_dapai;
    private ImageView iv_fenlei;

    public PinPaiViewHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;
        this.mItemView = itemView;

    }

    @Override
    public void setData(ShouYe.DataBean.ModuleBean moduleBean) {
        String module_key = moduleBean.getModule_key();
        if("bannerModule".equals(module_key)) {
            iv_pinpai = (ImageView) mItemView.findViewById(R.id.iv_pinpai);
            ShouYeModuleData data = moduleBean.getData().get(0);
            String img = data.getImg();
            Glide.with(mContext).load(img)
                    .placeholder(R.drawable.ic_error_page)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_pinpai);
        }

        if("imgListV3Module".equals(module_key)) {
            iv_dapai = (ImageView) mItemView.findViewById(R.id.iv_dapai);
            iv_fenlei = (ImageView) mItemView.findViewById(R.id.iv_fenlei);
            Glide.with(mContext).load(moduleBean.getData().get(0).getImg())
                    .placeholder(R.drawable.ic_error_page)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_dapai);
            Glide.with(mContext).load(moduleBean.getData().get(1).getImg())
                    .placeholder(R.drawable.ic_error_page)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_fenlei);
        }

    }
}
