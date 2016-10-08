package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.ShopDetailActivity;
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
    public void setData(final ShouYe.DataBean.ModuleBean moduleBean) {
        String module_key = moduleBean.getModule_key();
        if("bannerModule".equals(module_key)) {
            iv_pinpai = (ImageView) mItemView.findViewById(R.id.iv_pinpai);
            final ShouYeModuleData data = moduleBean.getData().get(0);
            String img = data.getImg();
            Glide.with(mContext).load(img)
                    .placeholder(R.drawable.ic_error_page)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_pinpai);

            iv_pinpai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = data.getTitle();
                    String url = data.getJump().getUrl();
                    jumpActivity(title,url);
                }
            });
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
            iv_dapai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = moduleBean.getData().get(0).getTitle();
                    String url = moduleBean.getData().get(0).getJump().getUrl();
                    jumpActivity(title, url);
                }
            });

            iv_fenlei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = moduleBean.getData().get(1).getTitle();
                    String url =moduleBean.getData().get(1).getJump().getUrl();
                    jumpActivity(title, url);
                }
            });
        }

    }

    private void jumpActivity(String title,String url){
        Intent intent = new Intent(mContext,ShopDetailActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        mContext.startActivity(intent);
    }
}
