package com.zjz.picture_net.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zjz.picture_net.R;

import butterknife.ButterKnife;

/**
 * Created by zjz on 2018/1/25.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResId());
        ButterKnife.bind(this);
        initData();
        initView();
    }

    protected abstract int setLayoutResId();

    protected abstract void initData();

    protected abstract void initView();

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
