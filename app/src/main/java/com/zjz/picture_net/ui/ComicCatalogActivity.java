package com.zjz.picture_net.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zjz.picture_net.R;
import com.zjz.picture_net.adapter.ComicCatalogAdapter;
import com.zjz.picture_net.base.BaseActivity;
import com.zjz.picture_net.contract.IComicCatalogContract;
import com.zjz.picture_net.entity.ComicCatalogAndUrl;
import com.zjz.picture_net.listener.OnClickRecyclerViewListener;
import com.zjz.picture_net.presenter.CatalogPresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class ComicCatalogActivity extends BaseActivity implements IComicCatalogContract.View , OnClickRecyclerViewListener {

    private static final String INTENT_URL = "URL";
    private static final String INTENT_IMG_URL = "img_url";
    @BindView(R.id.sv_catalog)
    SimpleDraweeView mSvCatalog;
    @BindView(R.id.tv_catalog_description)
    TextView mTvCatalogDescription;
    @BindView(R.id.rv_catalog)
    RecyclerView mRvCatalog;
    private CatalogPresenterImpl mCatalogPresenter;
    private String mThisUrl;
    private String mImgURL;

    private ArrayList<ComicCatalogAndUrl> mCatalogList ;
    private ComicCatalogAdapter mComicCatalogAdapter;

    public static void gotoActivityByUrl(Context context, String thisUrl, String imgUrl) {
        Intent intent = new Intent(context, ComicCatalogActivity.class);
        intent.putExtra(INTENT_URL, thisUrl);
        intent.putExtra(INTENT_IMG_URL, imgUrl);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_comic_catalog;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String catalogUrl = intent.getStringExtra(INTENT_URL);
        String imgUrl = intent.getStringExtra(INTENT_IMG_URL);
        mThisUrl = catalogUrl;
        mImgURL = imgUrl;

        mCatalogPresenter = new CatalogPresenterImpl(this, this);
        mCatalogList = new ArrayList<>();

        mSvCatalog.setImageURI(mImgURL);

        //适配器和RecyclerView初始化
        mComicCatalogAdapter = new ComicCatalogAdapter();
        mComicCatalogAdapter.setOnRecyclerViewListener(this);
        mRvCatalog.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvCatalog.setItemAnimator(new DefaultItemAnimator());
        mRvCatalog.setAdapter(mComicCatalogAdapter);
        //加载内容
        mCatalogPresenter.showComicInformation(mThisUrl);
    }

    @Override
    protected void initView() {


    }

    @Override
    public void onShowComicDescriptionSucceed(String text) {
        mTvCatalogDescription.setText(text);
    }

    @Override
    public void onShowComicDescriptionFailed(String reason) {

    }

    @Override
    public void onShowComicCatalogSucceed(ArrayList<ComicCatalogAndUrl> comicInfoArrayList) {
        mCatalogList = comicInfoArrayList;
        mComicCatalogAdapter.updateData(mCatalogList);
    }

    @Override
    public void onShowComicCatalogFailed(String reason) {

    }

    @Override
    public void onItemClick(int position) {
        ComicContentActivity.gotoActivityByUrl(this,mCatalogList.get(position).getUrl());
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }



}
