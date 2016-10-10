package com.example.spj.youfan.uiself;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spj.youfan.R;
import com.example.spj.youfan.utils.CacheUtils;

/**
 * Created by spj on 2016/10/10.
 */
public class MyPopWindow extends PopupWindow {

    private final String[] data;
    private Context mContext;
    private View view;
    private ListView lv;

    public MyPopWindow(Context context) {
        this.mContext = context;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popup_window, null);
        lv = (ListView) view.findViewById(R.id.listview);
        data = new String[]{"男生", "女生", "生活"};
        MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);

        // 设置外部可点击
        this.setOutsideTouchable(true);

        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.listview).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);

        //设置listview的点击事件
        initListener();

    }

    private OnPopItemClickListener mOnItemClickListener = null;

    public void setmOnItemClickListener(OnPopItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    //自定义Recycleview点击事件的监听
    public interface OnPopItemClickListener {
        void onItemClick(String name);
    }

    private void initListener() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "aaa"+data[position], Toast.LENGTH_SHORT).show();
                dismiss();
                //将点击的缓存
                CacheUtils.putString(mContext, "name", data[position]);
                if(mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(data[position]);
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(mContext);
            tv.setText(data[position]);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setBackgroundResource(R.drawable.pop_item_selector);
            tv.setBackgroundColor(Color.WHITE);
            tv.setPadding(0,10,0,10);
            return tv;
        }
    }
}

