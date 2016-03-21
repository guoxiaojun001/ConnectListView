package com.maning.connectlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by maning on 16/1/25.
 */
public class SearchAreaFirstAdapter extends BaseAdapter {

    private List<String> mDatas;
    private Context mContext;
    private final LayoutInflater layoutInflater;
    private int mPosition;
    private int type;   //默认0：一级菜单，1：二级菜单，2：三级菜单

    public SearchAreaFirstAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_item_search_area, parent, false);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mPosition == position) {
            viewHolder.textView.setBackgroundColor(mContext.getResources().getColor(R.color.colorSecondItemBackground));
            viewHolder.textView.setTextColor(mContext.getResources().getColor(R.color.colorItemTextRed));
        } else {
            viewHolder.textView.setBackgroundColor(mContext.getResources().getColor(R.color.colorFirstItemBackground));
            viewHolder.textView.setTextColor(mContext.getResources().getColor(R.color.colorItemTextBlack));
        }
        viewHolder.textView.setText(mDatas.get(position));

        return convertView;
    }

    public void setSelectPosition(int position) {
        mPosition = position;
    }

    public String getCurrentPositionItem() {
        return mDatas.get(mPosition);
    }

    private static class ViewHolder {
        TextView textView;
    }

}
