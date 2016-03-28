package com.xs.xsgridimgload.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xs.xsgridimgload.R;

import java.util.List;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-03-28 14:55
 * @email Xs.lin@foxmail.com
 */
public class TextGridViewAdapter extends BaseAdapter {

    private List<String> datas;
    private Context mContext;
    public TextGridViewAdapter(Context context,List<String> datas) {
        this.datas =datas;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView textView = new TextView(mContext);
        textView.setText(datas.get(position));

        textView.setPadding(50, 0, 50, 0);
        textView.setTextColor(mContext.getResources().getColor(R.color.color_black));
        textView.setTextSize(15f);
        return textView;
    }

}
