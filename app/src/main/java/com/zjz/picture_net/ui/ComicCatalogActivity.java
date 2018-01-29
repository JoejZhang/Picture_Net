package com.zjz.picture_net.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.zjz.picture_net.R;
import com.zjz.picture_net.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComicCatalogActivity extends BaseActivity {

    private static final String INTENT_NAME = "URL";

    public static void gotoActivityByUrl(Context context, String catalogUrl){
        Intent intent = new Intent(context , ComicCatalogActivity.class);
        intent.putExtra(INTENT_NAME,catalogUrl);
        context.startActivity(intent);
    }

    @BindView(R.id.btn_cata)
    Button mBtnCata;

    @Override
    protected int setLayoutResId() {

        return R.layout.activity_comic_catalog;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String calatogUrl = intent.getStringExtra(INTENT_NAME);

        mBtnCata.setText(calatogUrl);
    }

    @Override
    protected void initView() {

    }


    @OnClick(R.id.btn_cata)
    public void onViewClicked() {

    }
}
