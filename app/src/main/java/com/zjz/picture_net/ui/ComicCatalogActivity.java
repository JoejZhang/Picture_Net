package com.zjz.picture_net.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zjz.picture_net.R;
import com.zjz.picture_net.adapter.ComicCatalogAdapter;
import com.zjz.picture_net.base.BaseActivity;
import com.zjz.picture_net.contract.IComicCatalogContract;
import com.zjz.picture_net.entity.ComicCatalogAndUrl;
import com.zjz.picture_net.entity.ComicDescription;
import com.zjz.picture_net.listener.OnClickRecyclerViewListener;
import com.zjz.picture_net.presenter.CatalogPresenterImpl;
import com.zjz.picture_net.utils.GridSpacingItemDecoration;
import com.zjz.picture_net.view.ExpandableTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ComicCatalogActivity extends BaseActivity implements IComicCatalogContract.View, OnClickRecyclerViewListener {

    private static final String INTENT_URL = "URL";
    private static final String INTENT_IMG_URL = "img_url";
    @BindView(R.id.sv_catalog)
    SimpleDraweeView mSvCatalog;
    @BindView(R.id.tv_catalog_title)
    TextView mTvCatalogTitle;
    @BindView(R.id.tv_catalog_comic_title)
    TextView mTvCatalogComicTitle;
    @BindView(R.id.tv_catalog_comic_author)
    TextView mTvCatalogComicAuthor;
    @BindView(R.id.tv_catalog_comic_date)
    TextView mTvCatalogComicDate;
    @BindView(R.id.tv_catalog_comic_status)
    TextView mTvCatalogComicStatus;
    @BindView(R.id.expandable_catalog_description)
    ExpandableTextView mExpandableCatalogDescription;
    @BindView(R.id.id_expand_textview)
    TextView mIdExpandTextview;
    @BindView(R.id.rv_catalog)
    RecyclerView mRvCatalog;

    private CatalogPresenterImpl mCatalogPresenter;
    private String mThisUrl;
    private String mImgURL;

    private ArrayList<ComicCatalogAndUrl> mCatalogList;
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

        mRvCatalog.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mRvCatalog.addItemDecoration(new GridSpacingItemDecoration(3, getResources().getDimensionPixelSize(R.dimen.padding_middle), true));
        mRvCatalog.setHasFixedSize(true);
        mRvCatalog.setAdapter(mComicCatalogAdapter);
        //加载内容
        mCatalogPresenter.showComicInformation(mThisUrl);
    }

    @Override
    protected void initView() {


    }

    @Override
    public void onShowComicDescriptionSucceed(ComicDescription comicDescription) {
        mTvCatalogTitle.setText(comicDescription.getTitle());
        mTvCatalogComicTitle.setText(comicDescription.getTitle());
        mTvCatalogComicAuthor.setText(comicDescription.getAuthor());
        mTvCatalogComicDate.setText(comicDescription.getUpdateDate());
        mTvCatalogComicStatus.setText(comicDescription.getStatus());
        mIdExpandTextview.setVisibility(View.VISIBLE);
        mExpandableCatalogDescription.setText("漫画简介：\n"+ comicDescription.getDescription());
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
        ComicContentActivity.gotoActivityByUrl(this, mCatalogList.get(position).getUrl());
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
