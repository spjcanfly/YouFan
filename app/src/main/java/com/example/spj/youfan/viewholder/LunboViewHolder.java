package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.ShouYe;
import com.example.spj.youfan.bean.ShouYeModuleData;
import com.example.spj.youfan.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spj on 2016/9/29.
 * 轮播图
 * 在这个类中，查找属于它的控件
 */
public class LunboViewHolder extends BaseRecyviewViewHolder{

    private final Banner banner;
    private final View mItemView;
    private  TextView tv_shou_ye_chinese;
    private  TextView tv_shou_ye_english;
    private ImageView iv_shou_ye_common;

    public LunboViewHolder(Context context, View itemView) {
        super(context, itemView);
        this.mItemView = itemView;
        banner = (Banner) itemView.findViewById(R.id.banner);
    }

    @Override
    public void setData(ShouYe.DataBean.ModuleBean moduleBean) {
        String c_title = moduleBean.getC_title();
        if("流行资讯".equals(c_title)) {
            tv_shou_ye_chinese = (TextView) mItemView.findViewById(R.id.tv_shou_ye_chinese);
            tv_shou_ye_english = (TextView) mItemView.findViewById(R.id.tv_shou_ye_english);
            iv_shou_ye_common = (ImageView) mItemView.findViewById(R.id.iv_shou_ye_common);
            //更多的那个图片出现
            iv_shou_ye_common.setVisibility(View.VISIBLE);
            //流行资讯
            tv_shou_ye_chinese.setText(moduleBean.getC_title());
            tv_shou_ye_english.setText(moduleBean.getE_title());
        }

        List<ShouYeModuleData> datas = moduleBean.getData();
        List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            String img = datas.get(i).getImg();
            String title = datas.get(i).getTitle();
            images.add(img);
            if("流行资讯".equals(c_title)) {
                titles.add(title);
            }
        }
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        banner.isAutoPlay(true);
        //设置标题
        if(titles != null) {
            banner.setBannerTitles(titles);
        }
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}
