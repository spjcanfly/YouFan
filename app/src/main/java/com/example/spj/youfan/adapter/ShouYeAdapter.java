package com.example.spj.youfan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.ShouYe;
import com.example.spj.youfan.viewholder.DefaultHolder;
import com.example.spj.youfan.viewholder.JinRiXinPinViewHolder;
import com.example.spj.youfan.viewholder.LunboViewHolder;
import com.example.spj.youfan.viewholder.PinLeiViewHolder;
import com.example.spj.youfan.viewholder.PinPaiViewHolder;
import com.example.spj.youfan.viewholder.XinRenViewHolder;
import com.example.spj.youfan.viewholder.YouFanViewHolder;

import java.util.List;

/**
 * Created by spj on 2016/9/29.
 */
public class ShouYeAdapter extends RecyclerView.Adapter<BaseRecyviewViewHolder> {

    private final Context mContext;
    private final List<ShouYe.DataBean.ModuleBean> datas;

    //轮播图
    private static final int TOPIMAGE = 0;
    //今日新品，人气单品
    private static final int ICON = 1;

    //新品Chok好货 vol.22
    private static final int BANNER = 2;

    //新人专享
    private static final int NEW = 3;

    //热门品类
    private static final int HOTCATE = 4;

    //热门品牌
    private static final int HOTBRAND = 5;

    //搭配趋势
    private static final int COLLOSPECIAL = 6;

    //流行资讯
    private static final int IMG = 7;

    //上装
    private static final int LISTV1 = 8;

    //今日最大牌
    private static final int LISTV3 = 9;

    //新入驻品牌
    private static final int LISTV4 = 10;

    //猜你喜欢
    private static final int LIKE = 11;

    //有范公告
    private static final int NOTICE =12;

    private BaseRecyviewViewHolder baseRecyviewViewHolder;
    private View convertView;


    public ShouYeAdapter(Context mContext, List<ShouYe.DataBean.ModuleBean> bean) {
        this.mContext = mContext;
        this.datas = bean;
    }

    @Override
    public BaseRecyviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //用这种加载布局，可以少些一个节点，false，不添加到根部局
        convertView = LayoutInflater.from(mContext).inflate(R.layout.defualt_image, parent, false);
        baseRecyviewViewHolder = new DefaultHolder(mContext, convertView);
        switch (viewType) {
            //轮播图
            case TOPIMAGE:
//                convertView = View.inflate(mContext, R.layout.top_play_image, null);
                convertView = LayoutInflater.from(mContext).inflate(R.layout.top_play_image,parent,false);
                //将布局传递给HomeViewHolder，便于找控件，显示
                baseRecyviewViewHolder = new LunboViewHolder(mContext, convertView);
                break;
            //今日新品，人气单品
            case ICON:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.jinri_xinpin_recycle,parent,false);
                //将布局传递给HomeViewHolder，便于找控件，显示
                baseRecyviewViewHolder = new JinRiXinPinViewHolder(mContext, convertView);
                break;
            //新品Chok好货 vol.22
            case BANNER:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.pin_pai_item,parent,false);
                //将布局传递给HomeViewHolder，便于找控件，显示
                baseRecyviewViewHolder = new PinPaiViewHolder(mContext, convertView);
                break;
            //新人专享
            case NEW:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.xin_ren_item,parent,false);
                //将布局传递给HomeViewHolder，便于找控件，显示
                baseRecyviewViewHolder = new XinRenViewHolder(mContext, convertView);
                break;
            //热门品类
            case HOTCATE:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.pin_lei_recycle,parent,false);
                //将布局传递给HomeViewHolder，便于找控件，显示
                baseRecyviewViewHolder = new PinLeiViewHolder(mContext, convertView);
                break;
            //热门品牌
            case HOTBRAND:

                break;
            //搭配趋势
            case COLLOSPECIAL:

                break;
            //流行资讯
            case IMG:

                break;
            //上装
            case LISTV1:

                break;
            //今日最大牌
            case LISTV3:

                break;
            //新入驻品牌
            case LISTV4:

                break;
            //猜你喜欢
            case LIKE:

                break;
            //有范公告
            case NOTICE:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.youfan_notice_item,parent,false);
                //将布局传递给HomeViewHolder，便于找控件，显示
                baseRecyviewViewHolder = new YouFanViewHolder(mContext, convertView);
                break;

        }

        return baseRecyviewViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyviewViewHolder holder, int position) {
        baseRecyviewViewHolder.setData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        //根据位置，从列表中得到一个数据对象和数据类型
        String module_key = datas.get(position).getModule_key();
        switch (module_key) {
            case "topImgModule":
                itemViewType = TOPIMAGE;
                break;
            case "iconModule":
                itemViewType = ICON;
                break;
            case "bannerModule":
                itemViewType = BANNER;
                break;
            case "newModule":
                itemViewType = NEW;
                break;
            case "hotCateModule":
                itemViewType = HOTCATE;
                break;
            case "hotBrandModule":
                itemViewType = HOTBRAND;
                break;
            case "colloSpecialModule":
                itemViewType = COLLOSPECIAL;
                break;
            case "imgModule":
                itemViewType = IMG;
                break;
            case "imgListV1Module":
                itemViewType = LISTV1;
                break;
            case "imgListV3Module":
                itemViewType = LISTV3;
                break;
            case "imgListV4Module":
                itemViewType = LISTV4;
                break;
            case "likeModule":
                itemViewType = LIKE;
                break;
            case "noticeModule":
                itemViewType = NOTICE;
                break;

        }
        return itemViewType;
    }


}
