package com.example.spj.youfan.pager;

import android.content.Context;
import android.os.Handler;
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
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
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
 * 灵感的具体
 */
public class TabDetailPager {

    private final Context mContext;
    private String mName;
    public View rootView;
    private RecyclerView recycleview;
    private String url;
    private List<LingGanZiXun.DataBean.ListBean> lists;
    private PtrClassicFrameLayout test_recycler_view_frame;
    private Handler handler = new Handler();

    public TabDetailPager(Context context, String name) {
        this.mContext = context;
        this.mName = name;
        rootView = initView();
    }

    private View initView() {
        View view = View.inflate(mContext, R.layout.linggan_recycle, null);
        recycleview = (RecyclerView) view.findViewById(R.id.recycleview);
        test_recycler_view_frame = (PtrClassicFrameLayout) view.findViewById(R.id.test_recycler_view_frame);
        test_recycler_view_frame.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        for (int i = 0; i < 17; i++) {
//                            mData.add(new String("  ListView item  -" + i));
//                        }
//                        mAdapter.notifyDataSetChanged();
                        test_recycler_view_frame.refreshComplete();

//                        if (!test_recycler_view_frame.isLoadMoreEnable()) {
//                            test_recycler_view_frame.setLoadMoreEnable(true);
//                        }
                    }
                }, 1500);
            }
        });
        return view;
    }

    public void initData() {
        //请求的地址
        if ("全部".equals(mName)) {
            url = Constants.LG_1;
        }else if("实验室".equals(mName)) {
            url = Constants.LG_2;
        }else if("咖啡馆".equals(mName)) {
            url = Constants.LG_3;
        }else if("趋势".equals(mName)) {
            url = Constants.LG_4;
        }else if("热闻".equals(mName)) {
            url = Constants.LG_6;
        }else if("特辑".equals(mName)) {
            url = Constants.LG_9;
        }else if("生活".equals(mName)) {
            url = Constants.LG_10;
        }else if("健身房".equals(mName)) {
            url = Constants.LG_12;
        }
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
            MyInspirationAdapter adapter = new MyInspirationAdapter(mContext);
//            RecyclerAdapterWithHF madapter = new RecyclerAdapterWithHF(adapter);
            recycleview.setAdapter(adapter);
            recycleview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }
    }

    //解析json
    private LingGanZiXun parsedJson(String json) {

        return new Gson().fromJson(json, LingGanZiXun.class);
    }

    class MyInspirationAdapter extends RecyclerView.Adapter<ViewHolder> {
        public MyInspirationAdapter(Context mContext) {
            super();
        }

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
