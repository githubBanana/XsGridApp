package com.xs.xsgridimgload.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.xs.xsgridimgload.R;
import com.xs.xsgridimgload.utils.PhoneInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0 <显示 TextView 的GridView>
 * @author: Xs
 * @date: 2016-03-28 10:02
 * @email Xs.lin@foxmail.com
 */
public class TextGridFragment extends Fragment{
    private static final String TAG = "TextGridFragment";

    private GridView mGridView ;
    private TextGridViewAdapter mAdapter;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.text_grid_fragment_layout,container,false);
        findView();
        initData();
        return view;
    }

    private void findView() {
        mGridView = (GridView) view.findViewById(R.id.text_grid);
    }

    private void initData() {
        List<String> datas = new ArrayList<>();
        datas.add("#健康快乐1");
        datas.add("#健康快乐2");
        datas.add("#健康快乐3");
        datas.add("#健康快乐4");
        datas.add("#健康快乐5");
        datas.add("#健康快乐6");

    }

    public void importData(List<String> datas) {
        mAdapter = new TextGridViewAdapter(datas);
        mGridView.setAdapter(mAdapter);
    }

    private class TextGridViewAdapter extends BaseAdapter {

        private List<String> datas;

        public TextGridViewAdapter(List<String> datas) {
            this.datas =datas;
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
            Log.i(TAG, "position:" + position + " data.size=" + datas.size() + " str:" + datas.get(position));
            final TextView textView = new TextView(getActivity());
            textView.setText(datas.get(position));

            textView.setPadding(50, 0, 50, 0);
            textView.setTextColor(getResources().getColor(R.color.color_black));
            textView.setTextSize(15f);
            return textView;
        }

    }
}
