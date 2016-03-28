package com.xs.xsgridimgload.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xs.xsgridimgload.R;
import com.xs.xsgridimgload.utils.BitmapSampleUtil;
import com.xs.xsgridimgload.view.SpaceImageDetailActivity;
import com.xs.xsgridimgload.view.SquareCenterImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0 <显示 ImageView 的 GridView>
 * @author: Xs
 * @date: 2016-03-28 00:10
 */
public class ImageGridFragment extends Fragment{
    private static final String TAG = "GridFragment";

    public static DisplayImageOptions mNormalImageOptions;
    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().toString();
    public static final String IMAGES_FOLDER = SDCARD_PATH + File.separator + "demo" + File.separator + "images" + File.separator;

    private GridView mGridView;

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.image_grid_fragment_layout, container, false);
        initImageLoader(getActivity());
        findView();
        initData();
        return view;
    }

    private void findView() {
        mGridView = (GridView) view.findViewById(R.id.multi_photo_grid);
    }

    private void initData() {
        List<String> datas = new ArrayList<>();
        for (int i = 0;i < 5;i++)
            datas.add(BitmapSampleUtil.getBmpUrl());
        mGridView.setAdapter(new ImageGridViewAdapter(datas));
    }

    private void initImageLoader(Context context) {
        int memoryCacheSize = (int) (Runtime.getRuntime().maxMemory() / 5);
        MemoryCache memoryCache;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            memoryCache = new LruMemoryCache(memoryCacheSize);
        } else {
            memoryCache = new LRULimitedMemoryCache(memoryCacheSize);
        }

        mNormalImageOptions = new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565).cacheInMemory(true).cacheOnDisc(true)
                .resetViewBeforeLoading(true).build();

        // This
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).defaultDisplayImageOptions(mNormalImageOptions)
                .denyCacheImageMultipleSizesInMemory().discCache(new UnlimitedDiskCache(new File(IMAGES_FOLDER)))
                        // .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(memoryCache)
                        // .memoryCacheSize(memoryCacheSize)
                .tasksProcessingOrder(QueueProcessingType.LIFO).threadPriority(Thread.NORM_PRIORITY - 2).threadPoolSize(3).build();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    private class ImageGridViewAdapter extends BaseAdapter{

        private List<String> datas;
        public ImageGridViewAdapter(List<String> datas) {
            this.datas = datas;
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
            final SquareCenterImageView imageView = new SquareCenterImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.getInstance().displayImage(datas.get(position), imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), SpaceImageDetailActivity.class);
                    intent.putExtra("images", (ArrayList<String>) datas);
                    intent.putExtra("position", position);
                    int[] location = new int[2];
                    imageView.getLocationOnScreen(location);
                    intent.putExtra("locationX", location[0]);
                    intent.putExtra("locationY", location[1]);

                    intent.putExtra("width", imageView.getWidth());
                    intent.putExtra("height", imageView.getHeight());
                    startActivity(intent);
                    getActivity().overridePendingTransition(0, 0);
                }
            });
            return imageView;
        }
    }
}
