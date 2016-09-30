package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.view.View;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.ShouYe;
import com.example.spj.youfan.bean.ShouYeModuleData;
import com.example.spj.youfan.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spj on 2016/9/29.
 * 轮播图
 * 在这个类中，查找属于它的控件
 */
public class LunboViewHolder extends BaseRecyviewViewHolder{

    private final Banner banner;

    public LunboViewHolder(Context context, View itemView) {
        super(context, itemView);

        banner = (Banner) itemView.findViewById(R.id.banner);
    }

    @Override
    public void setData(ShouYe.DataBean.ModuleBean moduleBean) {
        List<ShouYeModuleData> datas = moduleBean.getData();
        List<String> images = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            String img = datas.get(i).getImg();
            images.add(img);
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        banner.isAutoPlay(true);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}
