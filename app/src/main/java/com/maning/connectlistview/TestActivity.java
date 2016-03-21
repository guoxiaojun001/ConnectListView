package com.maning.connectlistview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    private ListView listView;
    private LinearLayout ll_top;

    private MyAdapter myAdapter;
    private boolean scrollFlag = false;// 标记是否滑动
    private int lastVisibleItemPosition;// 标记上次滑动位置

    private Animation animation01;
    private Animation animation02;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initView();

        initAnimation();

        initListView();
    }

    private void initAnimation() {
        animation01 = AnimationUtils.loadAnimation(this, R.anim.translate);
        animation02 = AnimationUtils.loadAnimation(this, R.anim.translate_back);
    }

    private void initListView() {
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        View inflate = LayoutInflater.from(TestActivity.this).inflate(R.layout.listview_head_empty, null, false);
        listView.addHeaderView(inflate);
    }

    private void initView() {

        listView = (ListView) findViewById(R.id.listView);
        ll_top = (LinearLayout) findViewById(R.id.ll_top);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Log.i(TAG, "SCROLL_STATE_TOUCH_SCROLL");
                        //触摸滑动
                        scrollFlag = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        Log.i(TAG, "SCROLL_STATE_IDLE");
                        //空闲状态
                        scrollFlag = false;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        Log.i(TAG, "SCROLL_STATE_FLING");
                        //惯性滑动
                        scrollFlag = true;
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (scrollFlag) {
                    if (firstVisibleItem > lastVisibleItemPosition) {
                        Log.i(TAG, "上滑");
                        //隐藏搜索
                        dismissTop();
                    }
                    if (firstVisibleItem < lastVisibleItemPosition) {
                        Log.i(TAG, "下滑");
                        //显示出搜索
                        showTop();
                    }
                    if (firstVisibleItem == lastVisibleItemPosition) {
                        return;
                    }
                    lastVisibleItemPosition = firstVisibleItem;
                }
            }
        });

    }

    private boolean dismissFlag = true;

    private void dismissTop() {
        if (ll_top.getVisibility() == View.VISIBLE) {
            if (!dismissFlag) {
                return;
            }
            dismissFlag = false;
            if (animation01 != null) {
                animation01.cancel();
            }
            ll_top.setVisibility(View.GONE);
            ll_top.startAnimation(animation01);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismissFlag = true;
                }
            }, 500);
        }
    }

    private boolean flag = true;

    private void showTop() {
        if (!(ll_top.getVisibility() == View.VISIBLE)) {
            if (!flag) {
                return;
            }
            flag = false;
            if (animation02 != null) {
                animation02.cancel();
            }
            ll_top.setVisibility(View.VISIBLE);
            ll_top.startAnimation(animation02);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    flag = true;
                }
            }, 500);
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(TestActivity.this).inflate(R.layout.list_item_search_area, parent, false);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText("哈哈" + position);
            return convertView;
        }
    }

    private static class ViewHolder {
        TextView textView;
    }

}
