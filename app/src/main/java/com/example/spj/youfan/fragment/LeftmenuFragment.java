package com.example.spj.youfan.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cjj.Util;
import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.MainActivity;
import com.example.spj.youfan.base.BaseFragment;

/**
 * Created by spj on 2016/9/27.
 * 侧滑的设置
 */
public class LeftmenuFragment extends BaseFragment {

    private int goPreposition;
    private LeftmenuFragmentAdapter adapter;
    private String data;
    private ListView listView;
    private String[] clothType;

    @Override
    public View initView() {
        //创建listview，并设置他的属性
        listView = new ListView(mContext);
        listView.setPadding(0, Util.dip2px(mContext, 40), 0, 0);
        listView.setDivider(new ColorDrawable(Color.WHITE));
        listView.setDividerHeight(10);//设置分割线
        listView.setCacheColorHint(Color.TRANSPARENT);

        //设置listview的item点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //点击后记录位置
                goPreposition = position;

                //点击后关闭左侧菜单
                MainActivity mainActivity = (MainActivity) mContext;
                //关闭菜单
                mainActivity.getSlidingMenu().toggle();

                //切换到对应的页面
                switchPager(goPreposition);
            }
        });
        return listView;
    }

    @Override
    public void initData() {
        super.initData();

        //为listview准备数据
        setData();
    }

    private void setData() {
        clothType = new String[]{"女生\t\tWOMEN","男生\t\tMEN","生活\t\tLIFESTYLE"};
        adapter = new LeftmenuFragmentAdapter();
        listView.setAdapter(adapter);

        //设置默认的页面
        switchPager(0);
    }

    private void switchPager(int goPreposition) {
        MainActivity mainActivity = (MainActivity) mContext;
        ContentFragment contentFragment = mainActivity.getContentFragment();

    }

    private class LeftmenuFragmentAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return clothType.length;
        }

        @Override
        public Object getItem(int position) {
            return clothType[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(mContext, R.layout.item_leftmenu, null);
            textView.setText(clothType[position]);
            return textView;
        }
    }
}
