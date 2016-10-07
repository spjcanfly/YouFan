package com.example.spj.youfan.pager;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseSortViewPager;
import com.example.spj.youfan.bean.PinLeiNan;
import com.example.spj.youfan.utils.CacheUtils;
import com.example.spj.youfan.utils.Constants;
import com.example.spj.youfan.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by spj on 2016/10/7.
 */
public class SortPinLeiDetailPager extends BaseSortViewPager{

    private ExpandableListView eplistview;
    private String url;
    private List<PinLeiNan.DataBean> datas;


    public SortPinLeiDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        url = Constants.KIND_PINLEI;
        View view = View.inflate(mContext, R.layout.sort_pager_item, null);
        eplistview = (ExpandableListView) view.findViewById(R.id.eplistview);
        return view;
    }

    @Override
    public void initData() {
        //把之前的缓存取出
        String saveJason = CacheUtils.getString(mContext, url);
        if (!TextUtils.isEmpty(saveJason)) {
            //解析数据和处理数据
            processData(saveJason);
        }
        //联网请求数据
        getDataFromNet();
    }



    private void getDataFromNet() {
        //使用OKhttp第三方封装库请求网络
        OkHttpUtils.get()
                .url(url)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("使用okhttp联网请求失败==品类" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("使用okhttp联网请求数据成功==品牌" + response);
                        //缓存的数据放到sp存储中
                        CacheUtils.putString(mContext, url, response);
                        processData(response);
                    }
                });
    }

    private void processData(String response) {
        PinLeiNan bean = parsedJson(response);
        //一个多少条数据
        datas = bean.getData();
        if(datas != null && datas.size()>0) {
            //有数据
            MyExpandableListAdapter adapter = new MyExpandableListAdapter();
            eplistview.setAdapter(adapter);
        }
    }

    //解析品类的json
    private PinLeiNan parsedJson(String json) {
            return new Gson().fromJson(json, PinLeiNan.class);
    }

     class MyExpandableListAdapter extends BaseExpandableListAdapter{

         //父的大小
         @Override
         public int getGroupCount() {
             return datas.size();
         }

         //每条父亲里面孩子的数量
         @Override
         public int getChildrenCount(int groupPosition) {
             return datas.get(groupPosition).getSubs().size();
         }


         @Override
         public Object getGroup(int groupPosition) {
             return datas.get(groupPosition).getImg();
         }

         //得到子item需要关联的数据
         @Override
         public Object getChild(int groupPosition, int childPosition) {

             return datas.get(groupPosition).getSubs().get(childPosition);
         }

         @Override
         public long getGroupId(int groupPosition) {
             return groupPosition;
         }

         @Override
         public long getChildId(int groupPosition, int childPosition) {
             return childPosition;
         }

         @Override
         public boolean hasStableIds() {
             return true;
         }

         @Override
         public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
             if(convertView == null) {
                 convertView = LayoutInflater.from(mContext).inflate(R.layout.sort_pinlei_parent, parent, false);
             }
             ImageView iv_sort_pinlei = (ImageView) convertView.findViewById(R.id.iv_sort_pinlei);
             Glide.with(mContext).load(datas.get(groupPosition).getImg()).
                     placeholder(R.drawable.ic_error_page)
                     .diskCacheStrategy(DiskCacheStrategy.ALL)
                     .into(iv_sort_pinlei);
             return convertView;
         }

         @Override
         public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
             if(convertView == null) {
                 convertView = LayoutInflater.from(mContext).inflate(R.layout.sort_pinlei_child,parent,false);
             }
             TextView tv_sort_pinlei = (TextView) convertView.findViewById(R.id.tv_sort_pinlei);
             tv_sort_pinlei.setText(datas.get(groupPosition).getSubs().get(childPosition).getCate_name());
             return convertView;
         }

         @Override
         public boolean isChildSelectable(int groupPosition, int childPosition) {
             return true;
         }
     }
}