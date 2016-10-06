package com.example.spj.youfan.pager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.bean.LingGanZiXun;
import com.example.spj.youfan.utils.CacheUtils;
import com.example.spj.youfan.utils.Constants;
import com.example.spj.youfan.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by spj on 2016/10/5.
 */
public class TabDetailPager {

    private final Context mContext;
    private String mId;
    public View rootView;
    private RecyclerView recycleview;
    private String url;
    private List<LingGanZiXun.DataBean.ListBean> lists;

    public TabDetailPager(Context context, String id) {
        this.mContext = context;
        this.mId = id;
        rootView = initView();
    }

    private View initView() {
        View view = View.inflate(mContext, R.layout.linggan_recycle, null);
        recycleview = (RecyclerView) view.findViewById(R.id.recycleview);
        return view;
    }

    public void initData() {
        //这是全部那个页面的数据
        if ("".equals(mId)) {
            mId = "0";
        }
        //请求的地址
        url = Constants.LG + mId;
        LogUtil.e("url222222222222"+url);
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
                        LogUtil.e("使用okhttp联网请求失败==灵感资讯" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("使用okhttp联网请求数据成功==灵感资讯" + response);
                        //缓存的数据放到sp存储中
                        CacheUtils.putString(mContext, url, response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        LingGanZiXun bean = parsedJson(json);
        lists = bean.getData().getList();
        if (lists != null && lists.size() > 0) {
            //有数据,准备适配器
            MyInspirationAdapter adapter = new MyInspirationAdapter();
            recycleview.setAdapter(adapter);
            recycleview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }
    }

    //解析json
    private LingGanZiXun parsedJson(String json) {

        return new Gson().fromJson(json, LingGanZiXun.class);
    }

    class MyInspirationAdapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.inspiration_item, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String img = lists.get(position).getImg();
            String title = lists.get(position).getTitle();

            Glide.with(mContext).load(img).
                    placeholder(R.drawable.ic_error_page)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.iv_inspiration);

            holder.tv_inspiration.setText(title);
        }

        @Override
        public int getItemCount() {
            return lists == null ?0:lists.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

         ImageView iv_inspiration;
         TextView tv_inspiration;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_inspiration = (ImageView) itemView.findViewById(R.id.iv_inspiration);
            tv_inspiration = (TextView) itemView.findViewById(R.id.tv_inspiration);
        }

    }
}
