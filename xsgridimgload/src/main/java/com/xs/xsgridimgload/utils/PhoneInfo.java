package com.xs.xsgridimgload.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-03-28 10:38
 * @email Xs.lin@foxmail.com
 */
public class PhoneInfo {
    private static final String TAG = "PhoneInfo";

    public static float getScreenHeight(Context context) {
        Resources mResources = context.getResources();
        DisplayMetrics dm = mResources.getDisplayMetrics();
        return dm.heightPixels;
    }
    public static float getScreenWidth(Context context) {
        Resources mResources = context.getResources();
        DisplayMetrics dm = mResources.getDisplayMetrics();
        return dm.widthPixels;
    }
}
