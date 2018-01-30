package com.zjz.picture_net.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zjz.picture_net.R;
import com.zjz.picture_net.adapter.ComicContentAdapter;
import com.zjz.picture_net.base.BaseActivity;
import com.zjz.picture_net.contract.IComicContentContract;
import com.zjz.picture_net.listener.OnClickRecyclerViewListener;
import com.zjz.picture_net.presenter.ComicContentPresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;

public class ComicContentActivity extends BaseActivity implements IComicContentContract.View, OnClickRecyclerViewListener{
    private static final String INTENT_URL = "URL";
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;

    private ComicContentPresenterImpl mComicContentPresenter;
    private ComicContentAdapter mComicContentAdapter;

    private ArrayList<String> mImgUrlList ;

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

        mImgUrlList = new ArrayList<>();
        //适配器和RecyclerView初始化
        mComicContentAdapter = new ComicContentAdapter();
        mComicContentAdapter.setOnRecyclerViewListener(this);
        mRvContent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvContent.setItemAnimator(new DefaultItemAnimator());
        mRvContent.setAdapter(mComicContentAdapter);

        mComicContentPresenter = new ComicContentPresenterImpl(this);
        mComicContentPresenter.showComicList(thisUrl);

    }

    @Override
    public void onShowComicSucceed(ArrayList<String> arrayList) {
 //       mImgUrlList = arrayList;
        mComicContentAdapter.appendData(arrayList);
    }

    @Override
    public void onShowComicFailed(String reason) {
        showToast(reason);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }


}
