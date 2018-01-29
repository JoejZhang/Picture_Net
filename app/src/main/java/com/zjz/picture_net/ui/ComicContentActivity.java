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

public class ComicContentActivity extends BaseActivity {
    private static final String INTENT_URL = "URL";
    @BindView(R.id.btn_content)
    Button mBtnContent;

    public static void gotoActivityByUrl(Context context, String thisUrl) {
        Intent intent = new Intent(context, ComicContentActivity.class);
        intent.putExtra(INTENT_URL, thisUrl);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_comic_content;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String thisUrl = intent.getStringExtra(INTENT_URL);
        mBtnContent.setText(thisUrl);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_content)
    public void onViewClicked() {
    }
}
