package com.zjz.picture_net;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjz.picture_net.adapter.MainComicRvAdapter;
import com.zjz.picture_net.base.BaseActivity;
import com.zjz.picture_net.contract.IMainContract;
import com.zjz.picture_net.entity.ComicInfo;
import com.zjz.picture_net.listener.OnClickRecyclerViewListener;
import com.zjz.picture_net.presenter.MainPresenterImpl;
import com.zjz.picture_net.ui.ComicCatalogActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity  implements OnClickRecyclerViewListener , IMainContract.View{

    @BindView(R.id.btn_get)
    Button mBtnGet;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.btn_previous)
    Button mBtnPrevious;
    @BindView(R.id.rv_main_comic)
    RecyclerView mRvMainComic;

    private String mNextUrl;
    private String mPreviousUrl;

    private MainComicRvAdapter mMainComicRvAdapter;

    private  ArrayList<ComicInfo> mComicInfoArrayList = new ArrayList<>();//漫画目录

    private MainPresenterImpl mMainPresenter;

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mMainPresenter = new MainPresenterImpl(this,this);

        mMainComicRvAdapter = new MainComicRvAdapter();
        mMainComicRvAdapter.setOnRecyclerViewListener(this);
        mRvMainComic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvMainComic.setItemAnimator(new DefaultItemAnimator());
        mRvMainComic.setAdapter(mMainComicRvAdapter);

        mMainPresenter.showComicList("http://comic.kukudm.com/");
    }


    @Override
    protected void initView() {

    }

    @Override
    public void onShowComicListSucceed(ArrayList<ComicInfo> comicInfoArrayList) {//成功后回调
        mComicInfoArrayList = comicInfoArrayList;
        mMainComicRvAdapter.updateData(mComicInfoArrayList);
    }

    @Override
    public void onShowComicListFailed(String reason) {
        showToast(reason);
    }

    @OnClick({R.id.btn_get, R.id.btn_next, R.id.btn_previous})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
            //    mMainPresenter.showComicList("http://comic.kukudm.com/");
                break;
            case R.id.btn_previous:
                break;
            case R.id.btn_next:
                break;
            default:
                break;

        }
    }

    @Override
    public void onItemClick(int position) {
       ComicCatalogActivity.gotoActivityByUrl(this,mComicInfoArrayList.get(position).getContentUrl(),mComicInfoArrayList.get(position).getImgUrl() ); //点击跳转
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }



    private void showPicture(final String url) {



    }



    @OnClick()
    public void onViewClicked() {
    }


}
