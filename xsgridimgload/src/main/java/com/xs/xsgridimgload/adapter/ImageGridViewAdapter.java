package com.xs.xsgridimgload.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xs.xsgridimgload.view.SpaceImageDetailActivity;
import com.xs.xsgridimgload.view.SquareCenterImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-03-28 14:57
 * @email Xs.lin@foxmail.com
 */
public class ImageGridViewAdapter extends BaseAdapter {

    private List<String> datas;
    private Context mContext;
    private OnListenCallback mOnListen;
    public ImageGridViewAdapter(Context context,List<String> datas) {
        this.datas = datas;
        this.mContext = context;
        this.mOnListen = (OnListenCallback) context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final SquareCenterImageView imageView = new SquareCenterImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.getInstance().displayImage(datas.get(position), imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SpaceImageDetailActivity.class);
                intent.putExtra("images", (ArrayList<String>) datas);
                intent.putExtra("position", position);
                int[] location = new int[2];
                imageView.getLocationOnScreen(location);
                intent.putExtra("locationX", location[0]);
                intent.putExtra("locationY", location[1]);

                intent.putExtra("width", imageView.getWidth());
                intent.putExtra("height", imageView.getHeight());
                mOnListen.touchPic(intent);
            }
        });
        return imageView;
    }

    public interface OnListenCallback {
        void touchPic(Intent intent);
    }
}
