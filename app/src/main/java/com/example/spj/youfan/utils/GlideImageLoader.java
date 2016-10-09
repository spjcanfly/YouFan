package com.example.spj.youfan.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by spj on 2016/9/30.
 * 加载轮播图的地址
 */
public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.fun_loading_0)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


}
