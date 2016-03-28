package com.xs.xsgridimgload.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xs.xsgridimgload.R;
import com.xs.xsgridimgload.adapter.ImageGridViewAdapter;
import com.xs.xsgridimgload.fragment.TextGridFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0 <测试 activity>
 * @author: Xs
 * @date: 2016-03-28 00:03
 */
public class TestActivity extends AppCompatActivity implements ImageGridViewAdapter.OnListenCallback {
    private static final String TAG = "TestActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_layout);
        final TextGridFragment fragment = (TextGridFragment) getSupportFragmentManager().findFragmentById(R.id.text_grid_fragment);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.importData(initData());
            }
        });
    }
    private List<String> initData() {
        List<String> datas = new ArrayList<>();
        datas.add("#嘻哈丰富2");
        datas.add("#嘻哈丰富1");
        datas.add("#嘻哈丰富2");
        datas.add("#嘻哈丰富5");
        return datas;
    }

    @Override
    public void touchPic(Intent intent) {
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
